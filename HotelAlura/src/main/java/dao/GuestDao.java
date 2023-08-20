package dao;

import model.Guest;
import utilities.ColumnsKey;
import utilities.InsetFieldGenerator;
import utilities.JOptionPane.ErrorMessages;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuestDao extends BaseDao<Guest> {
    private static final String INSERT_VALUES = "(?, ?, ?, ?, ?, ?)";
    private static final Integer UPDATE_VALUES_COUNT = 7;
    private static final String[] USED_COLUMNS = {
            ColumnsKey.NAME.getKey(), ColumnsKey.SURNAME.getKey(), ColumnsKey.BIRTHDATE.getKey(),
            ColumnsKey.NATIONALITY.getKey(), ColumnsKey.PHONE.getKey(), ColumnsKey.BOOKING_ID.getKey()
    };

    public GuestDao(Connection connection) {
        tableName = "guests";
        this.connection = connection;
    }

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
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, surname.concat("%"));
            statement.execute();
            try (ResultSet resultSet = statement.getResultSet()){
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

    public void insert(Guest guest) {
        String sql = String.format("INSERT INTO %s \n" +
                "%s VALUES %s", tableName, getInsertFields(), INSERT_VALUES);
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            preparedStatement(statement, guest);
            try (ResultSet resultSet = statement.getGeneratedKeys()){
                while (resultSet.next()) {
                    guest.setId(resultSet.getInt(1));
                    LOGGER.info(String.format("Saved with exit guest: %s", guest));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            ErrorMessages.showErrorMessage(
                    "Error while doing register",
                    "Error saving data");
            throw new RuntimeException(e);
        }
    }

    public Map<String, Integer> deleteEmbeddedBookings(Integer guestId, String bookingId) {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", tableName, ColumnsKey.ID.getKey());
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, guestId);
            statement.execute();
            BookingDao bookingsDao = new BookingDao(connection);
            Integer deletedBooking = bookingsDao.delete(bookingId);
            Map<String, Integer> deleted = new HashMap<>();
            deleted.put("Guest", statement.getUpdateCount());
            deleted.put("Booking", deletedBooking);
            return deleted;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setDate(3, guest.getBirthdate());
            statement.setString(4, guest.getNationality());
            statement.setString(5, guest.getPhone());
            statement.setString(6, guest.getBookingId());
        } catch (SQLException e) {
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
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.setDate(3, guest.getBirthdate());
            statement.setString(4, guest.getNationality());
            statement.setString(5, guest.getPhone());
            statement.setString(6, guest.getBookingId());
        } catch (SQLException e) {
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
            throw new RuntimeException(e);
        }
    }
}
