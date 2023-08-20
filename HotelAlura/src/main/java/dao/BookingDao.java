package dao;

import model.Booking;
import utilities.ColumnsKey;
import utilities.InsetFieldGenerator;
import utilities.JOptionPane.ErrorMessages;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao extends BaseDao<Booking> {
    private static final String INSERT_VALUES = "(?, ?, ?, ?, ?)";
    private static final Integer UPDATE_VALUES_COUNT = 5;
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

    public Integer delete(String  id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        try {
            LOGGER.info(sql + id);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            return statement.getUpdateCount();
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    public List<Booking> list(String bookingId) {
        List<Booking> result = new ArrayList<>();
        String sql = String.format(
                "SELECT %s %s %s %s %s FORM %s WHERE %s LIKE ?",
                ColumnsKey.ID.getKey(),
                ColumnsKey.CHECK_IN.getKey(),
                ColumnsKey.CHECK_OUT.getKey(),
                ColumnsKey.VALUE.getKey(),
                ColumnsKey.PAYMENT_METHOD.getKey(),
                tableName,
                ColumnsKey.ID.getKey()
                );
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, bookingId.concat("%"));
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                result.add(new Booking(
                        resultSet.getString(ColumnsKey.BOOKING_ID.getKey()),
                        resultSet.getDate(ColumnsKey.CHECK_IN.getKey()),
                        resultSet.getDate(ColumnsKey.CHECK_OUT.getKey()),
                        resultSet.getBigDecimal(ColumnsKey.VALUE.getKey()),
                        resultSet.getString(ColumnsKey.PAYMENT_METHOD.getKey())
                ));
            }
            return result;
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            ErrorMessages.showErrorMessage(
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
            String id = resultSet.getString(ColumnsKey.ID.getKey());
            Date checkIn = resultSet.getDate(ColumnsKey.CHECK_IN.getKey());
            Date checkOut = resultSet.getDate(ColumnsKey.CHECK_OUT.getKey());
            BigDecimal value = resultSet.getBigDecimal(ColumnsKey.VALUE.getKey());
            String payMethod = resultSet.getString(ColumnsKey.PAYMENT_METHOD.getKey());
            return new Booking(id, checkIn, checkOut, value, payMethod);
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
            statement.setString(5, booking.getPaymentMethod());
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
            statement.setString(5, booking.getPaymentMethod());
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
}
