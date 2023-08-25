package dao;

import model.Booking;
import utilities.InsetFieldGenerator;
import utilities.JOptionPane.UserShowMessages;
import utilities.StringUtilities;
import utilities.enums.ColumnsKey;
import utilities.enums.PaymentMethods;
import utilities.enums.TableNames;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public BookingDao(Connection connection) {
        this.connection = connection;
        tableName = "bookings";
    }

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

    public Integer[] deleteEmbeddedGuest(String bookingId) {
        Integer[] deletedRows = new Integer[2];
        int guestIdToDelete = getGuestIdFromBookingId(bookingId);

        GuestDao guestDao = new GuestDao(connection);
        deletedRows[1] = guestDao.delete(guestIdToDelete);

        deletedRows[0] = delete(bookingId);

        return deletedRows;
    }

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
            System.out.println(statement);
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

    @Override
    protected Booking createModel(ResultSet resultSet) {
        try {
            LOGGER.info("Creating Booking model");
            Integer id = resultSet.getInt(ColumnsKey.ID.getKey());
            String booking_id = resultSet.getString(ColumnsKey.BOOKING_ID.getKey());
            Date checkIn = resultSet.getDate(ColumnsKey.CHECK_IN.getKey());
            Date checkOut = resultSet.getDate(ColumnsKey.CHECK_OUT.getKey());
            BigDecimal value = resultSet.getBigDecimal(ColumnsKey.VALUE.getKey());
            PaymentMethods payMethod = StringUtilities.convertPaymentMethodStrToPaymentMethod(resultSet.getString(ColumnsKey.PAYMENT_METHOD.getKey()));
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
        return booking.getBookingId().length();
    }

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
