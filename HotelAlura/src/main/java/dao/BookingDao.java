package dao;

import model.Booking;
import utilities.InsetFieldGenerator;
import utilities.JOptionPane.UserShowMessages;
import utilities.StringUtilities;
import utilities.enums.ColumnsKey;
import utilities.enums.PaymentMethod;
import utilities.enums.TableNames;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Data Access Object (DAO) class for managing Booking entities.
 * This class provides methods to interact with the database table related to bookings.
 */
public class BookingDao extends BaseDao<Booking> {
    private static final String INSERT_VALUES = "(?, ?, ?, ?, ?)";
    private static final Integer UPDATE_VALUES_COUNT = 6;
    private static final String[] USED_COLUMNS = {
            ColumnsKey.BOOKING_ID.getKey(),
            ColumnsKey.CHECK_IN.getKey(),
            ColumnsKey.CHECK_OUT.getKey(),
            ColumnsKey.VALUE.getKey(),
            ColumnsKey.PAYMENT_METHOD.getKey(),
    };

    /**
     * Constructs a new instance of BookingDao.
     *
     * @param connection The database connection to use for operations.
     */
    public BookingDao(Connection connection) {
        this.connection = connection;
        tableName = "bookings";
    }

    /**
     * Deletes an object from the database table by its id.
     *
     * @param id The ID string of the object to delete.
     * @return The number of rows affected by the delete operation.
     */
    public Integer delete(String id) {
        String sql = String.format("DELETE FROM %s WHERE %s = ?",
                tableName, ColumnsKey.BOOKING_ID.getKey());
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.execute();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Deletes the booking and the guest objects from the database by them IDS.
     *
     * @param bookingId The BOOKING ID string of the object to delete.
     * @return The numbers of rows affected (booking and guest) by the delete operation.
     */
    public Integer[] deleteEmbeddedGuest(String bookingId) {
        Integer[] deletedRows = new Integer[2];
        int guestIdToDelete = getGuestIdFromBookingId(bookingId);

        GuestDao guestDao = new GuestDao(connection);
        deletedRows[1] = guestDao.delete(guestIdToDelete);

        deletedRows[0] = delete(bookingId);

        return deletedRows;
    }

    /**
     * Retrieves all objects from the database table searching by booking id.
     *
     * @param bookingId The BOOKING ID string to search in the database table.
     * @return An ArrayList containing all the objects retrieved from the table.
     */
    public List<Booking> list(String bookingId) {
        List<Booking> result = new ArrayList<>();
        String sql = String.format(
                "SELECT %s, %s, %s, %s, %s, %s FROM %s WHERE %s LIKE ?",
                ColumnsKey.ID.getKey(),
                ColumnsKey.BOOKING_ID.getKey(),
                ColumnsKey.CHECK_IN.getKey(),
                ColumnsKey.CHECK_OUT.getKey(),
                ColumnsKey.VALUE.getKey(),
                ColumnsKey.PAYMENT_METHOD.getKey(),
                tableName,
                ColumnsKey.BOOKING_ID.getKey()
        );
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookingId.concat("%"));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(new Booking(
                        resultSet.getInt(ColumnsKey.ID.getKey()),
                        resultSet.getString(ColumnsKey.BOOKING_ID.getKey()),
                        resultSet.getDate(ColumnsKey.CHECK_IN.getKey()),
                        resultSet.getDate(ColumnsKey.CHECK_OUT.getKey()),
                        resultSet.getBigDecimal(ColumnsKey.VALUE.getKey()),
                        StringUtilities.convertPaymentMethodStrToPaymentMethod(resultSet.getString(ColumnsKey.PAYMENT_METHOD.getKey()))
                ));
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            UserShowMessages.showErrorMessage(
                    "Try again",
                    "Error while retrieving data"
            );
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Creates a Booking model object from a result set.
     *
     * @param resultSet The result set containing data from the database.
     * @return A Booking instance created from the result set.
     */
    @Override
    protected Booking createModel(ResultSet resultSet) {
        try {
            LOGGER.info("Creating Booking model");
            Integer id = resultSet.getInt(ColumnsKey.ID.getKey());
            String booking_id = resultSet.getString(ColumnsKey.BOOKING_ID.getKey());
            Date checkIn = resultSet.getDate(ColumnsKey.CHECK_IN.getKey());
            Date checkOut = resultSet.getDate(ColumnsKey.CHECK_OUT.getKey());
            BigDecimal value = resultSet.getBigDecimal(ColumnsKey.VALUE.getKey());
            PaymentMethod payMethod = StringUtilities.convertPaymentMethodStrToPaymentMethod(resultSet.getString(ColumnsKey.PAYMENT_METHOD.getKey()));
            return new Booking(id, booking_id, checkIn, checkOut, value, payMethod);
        } catch (SQLException e) {
            LOGGER.error("Couldn't create booking model " + e.getMessage());
            throw new RuntimeException("Couldn't create booking model " + e);
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
    protected void setInsertStatementValues(PreparedStatement statement, Booking booking) {
        try {
            LOGGER.info("Setting insert statement values for guest model");
            statement.setString(1, booking.getBookingId());
            statement.setDate(2, (Date) booking.getCheckIn());
            statement.setDate(3, (Date) booking.getCheckOut());
            statement.setBigDecimal(4, booking.getValue());
            statement.setString(5, booking.getPaymentMethod().getMethod());
        } catch (SQLException e) {
            LOGGER.error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsetFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, Booking booking) {
        try {
            LOGGER.info("Setting update statement values for author model");
            statement.setString(1, booking.getBookingId());
            statement.setDate(2, (Date) booking.getCheckIn());
            statement.setDate(3, (Date) booking.getCheckOut());
            statement.setBigDecimal(4, booking.getValue());
            statement.setString(5, booking.getPaymentMethod().getMethod());
        } catch (SQLException e) {
            LOGGER.error("Couldn't sut update statements values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(Booking booking) {
        return booking.getId();
    }

    /**
     * Retrieves the id from the guests tables where the booking id where found.
     *
     * @param bookingId The BOOKING ID to search in the guest table.
     * @return The id from the guest.
     */
    private Integer getGuestIdFromBookingId(String bookingId) {
        String sql = String.format("""
                        SELECT g.%s
                        FROM %s b
                        JOIN %s g ON b.%s = g.%s
                        WHERE b.%s = ?""",
                ColumnsKey.ID.getKey(),
                tableName,
                TableNames.GUESTS.getKey(),
                ColumnsKey.BOOKING_ID.getKey(),
                ColumnsKey.BOOKING_ID.getKey(),
                ColumnsKey.BOOKING_ID.getKey()
        );

        int guestId = 0;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookingId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    guestId = resultSet.getInt(ColumnsKey.ID.getKey());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            throw new RuntimeException(ERROR_SQL_MESSAGE + e);
        }

        return guestId;
    }
}
