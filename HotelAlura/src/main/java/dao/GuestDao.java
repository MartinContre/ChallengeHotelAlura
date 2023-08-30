package dao;

import model.Guest;
import utilities.InsetFieldGenerator;
import utilities.enums.ColumnsKey;
import utilities.enums.TableNames;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Object (DAO) class for managing Guest entities.
 * This class provides methods to interact with the database table related to guests.
 */
public class GuestDao extends BaseDao<Guest> {
    private static final String INSERT_VALUES = "(?, ?, ?, ?, ?, ?)";
    private static final Integer UPDATE_VALUES_COUNT = 7;
    private static final String[] USED_COLUMNS = {
            ColumnsKey.NAME.getKey(), ColumnsKey.SURNAME.getKey(), ColumnsKey.BIRTHDATE.getKey(),
            ColumnsKey.NATIONALITY.getKey(), ColumnsKey.PHONE.getKey(), ColumnsKey.BOOKING_ID.getKey()
    };

    /**
     * Constructs a new instance of GuestDao.
     *
     * @param connection The database connection to use for operations.
     */
    public GuestDao(Connection connection) {
        tableName = TableNames.GUESTS.getKey();
        this.connection = connection;
    }

    /**
     * Creates a Guest model object from a result set.
     *
     * @param resultSet The result set containing data from the database.
     * @return A Guest instance created from the result set.
     */
    @Override
    protected Guest createModel(ResultSet resultSet) {
        try {
            LOGGER.info("Creating guest model");
            Integer id = resultSet.getInt(ColumnsKey.ID.getKey());
            String name = resultSet.getString(ColumnsKey.NAME.getKey());
            String surname = resultSet.getString(ColumnsKey.SURNAME.getKey());
            Date birthdate = resultSet.getDate(ColumnsKey.BIRTHDATE.getKey());
            String nationality = resultSet.getString(ColumnsKey.NATIONALITY.getKey());
            String phoneNumber = resultSet.getString(ColumnsKey.PHONE.getKey());
            String bookingId = resultSet.getString(ColumnsKey.BOOKING_ID.getKey());
            return new Guest(id, name, surname, birthdate, nationality, phoneNumber, bookingId);
        } catch (SQLException e) {
            LOGGER.error("Couldn't create guest model " + e.getMessage());
            throw new RuntimeException("Couldn't create guest model " + e);
        }
    }

    /**
     * Searches for guests by surname in the database.
     *
     * @param surname The surname to search for.
     * @return A list of Guest objects matching the search criteria.
     */
    public List<Guest> searchGuest(String surname) {
        List<Guest> result = new ArrayList<>();

        String sql = String.format("SELECT %s, %s, %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                ColumnsKey.ID.getKey(),
                ColumnsKey.NAME.getKey(),
                ColumnsKey.BIRTHDATE.getKey(),
                ColumnsKey.NATIONALITY.getKey(),
                ColumnsKey.PHONE.getKey(),
                ColumnsKey.BOOKING_ID.getKey(),
                ColumnsKey.SURNAME.getKey(),
                tableName,
                ColumnsKey.SURNAME.getKey());
        LOGGER.info("Getting list of guests by surname");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, surname.concat("%"));
            statement.execute();
            try (ResultSet resultSet = statement.getResultSet()) {
                while (resultSet.next()) {
                    result.add(new Guest(
                            resultSet.getInt(ColumnsKey.ID.getKey()),
                            resultSet.getString(ColumnsKey.NAME.getKey()),
                            resultSet.getString(ColumnsKey.SURNAME.getKey()),
                            resultSet.getDate(ColumnsKey.BIRTHDATE.getKey()),
                            resultSet.getString(ColumnsKey.NATIONALITY.getKey()),
                            resultSet.getString(ColumnsKey.PHONE.getKey()),
                            resultSet.getString(ColumnsKey.BOOKING_ID.getKey())
                    ));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            throw new RuntimeException(ERROR_SQL_MESSAGE + e);
        }
    }

    /**
     * Deletes a guest and its associated booking(s) from the database.
     *
     * @param guestId   The ID of the guest to be deleted.
     * @param bookingId The ID of the associated booking to be deleted.
     * @return An array containing the number of rows affected for guest deletion (index 0) and booking deletion (index 1).
     */
    public Integer[] deleteEmbeddedBookings(int guestId, String bookingId) {
        Integer[] deleted = new Integer[2];

        Integer deletedGuestRows = delete(guestId);

        BookingDao bookingsDao = new BookingDao(connection);
        Integer deletedBookingRows = bookingsDao.delete(bookingId);


        deleted[0] = deletedGuestRows;
        deleted[1] = deletedBookingRows;

        return deleted;
    }

    @Override
    protected String getInsertFields() {
        return InsetFieldGenerator.generateInsertFields(USED_COLUMNS);
    }

    @Override
    protected String getInsertValues() {
        return INSERT_VALUES;
    }

    @Override
    protected void setInsertStatementValues(PreparedStatement statement, Guest guest) {
        try {
            LOGGER.info("Setting insert statement values for guest model");
            preparedStatement(statement, guest);
        } catch (Exception e) {
            LOGGER.error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsetFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, Guest guest) {
        try {
            LOGGER.info("Setting update statement values for author model");
            preparedStatement(statement, guest);
        } catch (Exception e) {
            LOGGER.error("Couldn't sut update statements values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(Guest guest) {
        return guest.getId();
    }

    private void preparedStatement(PreparedStatement statement, Guest guest) {
        try {
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setDate(3, guest.getBirthdate());
            statement.setString(4, guest.getNationality());
            statement.setString(5, guest.getPhone());
            statement.setString(6, guest.getBookingId());
        } catch (SQLException e) {
            LOGGER.error("Couldn't prepared statements values: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
