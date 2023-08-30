package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * The abstract base class for Data Access Objects (DAOs).
 * This class provides common methods and functionality to interact with a database table.
 *
 * @param <T> The type of object this DAO works with.
 */
public abstract class BaseDao<T> {

    /**
     * The error message for SQL failures.
     */
    protected static String ERROR_SQL_MESSAGE = "Failed to execute SQL statement. ";
    /**
     * The logger for logging events and errors.
     */
    protected Logger LOGGER = LogManager.getLogger(BaseDao.class);
    /**
     * The database connection used for operations.
     */
    protected Connection connection;
    /**
     * The name of the database table associated with this DAO.
     */
    protected String tableName;

    /**
     * Retrieves all objects from the database table.
     *
     * @return An ArrayList containing all the objects.
     */
    public ArrayList<T> getAll() {
        ArrayList<T> result = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", tableName);
        LOGGER.info("Retrieving all the objects from the table: " + tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T model = createModel(resultSet);
                result.add(model);
            }
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    "Try later, an error occurred",
                    "Error while retrieving data",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Inserts a new object into the database table.
     *
     * @param model The object to insert.
     */
    public void insert(T model) {
        validateObject(model);
        String sql = String.format("INSERT INTO %s %s VALUES %s", tableName, getInsertFields(), getInsertValues());
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            LOGGER.info("Inserting object to table: " + tableName);
            statement.clearParameters();
            setInsertStatementValues(statement, model);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    while (resultSet.next()) {
                        LOGGER.info(String.format("Inserted %s into database with generated key: %d", model.getClass(), resultSet.getLong(1)));
                    }
                }
            }
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Updates an existing object in the database table.
     *
     * @param model The object to update.
     * @return The number of rows affected by the update operation.
     */
    public Integer update(T model) {
        validateObject(model);
        String sql = String.format("UPDATE %s SET %s WHERE id = ?", tableName, getUpdateFields());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setUpdateStatementValues(statement, model);
            statement.setLong(getUpdateStatementValuesCount(), getId(model));
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            String errorMessage = String.format("%s %s ", ERROR_SQL_MESSAGE, sql);
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Deletes an object from the database table by its ID.
     *
     * @param id The ID of the object to delete.
     * @return The number of rows affected by the delete operation.
     */
    public Integer delete(long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            LOGGER.info("Deleted user");
            statement.setLong(1, id);
            statement.executeUpdate();
            return statement.getUpdateCount();
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    /**
     * Creates a model object from a result set.
     *
     * @param resultSet The result set containing data from the database.
     * @return An instance of the model object created from the result set.
     */
    protected abstract T createModel(ResultSet resultSet);

    /**
     * Returns the fields to be used in the INSERT SQL statement.
     *
     * @return A string containing the fields for the INSERT statement.
     */
    protected abstract String getInsertFields();

    /**
     * Returns the values to be used in the INSERT SQL statement.
     *
     * @return A string containing the values for the INSERT statement.
     */
    protected abstract String getInsertValues();

    /**
     * Sets the values of a PreparedStatement for inserting a model into the database.
     *
     * @param statement The PreparedStatement to set values for.
     * @param model     The model containing data to be inserted.
     */
    protected abstract void setInsertStatementValues(PreparedStatement statement, T model);

    /**
     * Gets a comma-separated string of fields for use in an UPDATE statement.
     *
     * @return A string containing fields to be updated in an UPDATE statement.
     */
    protected abstract String getUpdateFields();

    /**
     * Sets the values of a PreparedStatement for updating a model in the database.
     *
     * @param statement The PreparedStatement to set values for.
     * @param model     The model containing updated data.
     */
    protected abstract void setUpdateStatementValues(PreparedStatement statement, T model);

    /**
     * Gets the count of values to be set in the UPDATE statement.
     *
     * @return The number of values to be set in an UPDATE statement.
     */
    protected abstract int getUpdateStatementValuesCount();

    /**
     * Gets the ID of the given model.
     *
     * @param model The model from which to retrieve the ID.
     * @return The ID of the model.
     */
    protected abstract long getId(T model);

    /**
     * Validates an object to ensure it is not null.
     *
     * @param model The object to validate.
     */
    protected void validateObject(T model) {
        if (model == null) {
            LOGGER.error("Model cannot be null");
        }
    }

}
