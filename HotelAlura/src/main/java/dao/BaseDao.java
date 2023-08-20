package dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public abstract class BaseDao<T> {

    protected Logger LOGGER = LogManager.getLogger(BaseDao.class);
    protected Connection connection;
    protected String tableName;
    protected static String ERROR_SQL_MESSAGE = "Failed to execute SQL statement. ";

    public BaseDao() {
    }

    protected abstract T createModel(ResultSet resultSet);

    /**
     * Retrieves all objects from the database table.
     *
     * @return An ArrayList containing all the objects.
      */
    public ArrayList<T> getAll() {
        ArrayList<T> result = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s", tableName);
        LOGGER.info("Retrieving all the objects from the table: " + tableName);
        try (PreparedStatement statement = connection.prepareStatement(sql)){
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
        System.out.println("problem");
        String sql = String.format("INSERT INTO %s %s VALUES %s", tableName, getInsertFields(), getInsertValues());
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            LOGGER.info("Inserting object to table: " + tableName);
            statement.clearParameters();
            setInsertStatementValues(statement, model);
            statement.executeUpdate();
            try (ResultSet resultSet= statement.getGeneratedKeys()){
                while (resultSet.next()) {
                    LOGGER.info(String.format("Inserted %s into database", model.getClass()));
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
     * @return
     */
    public Integer delete(long id) {
        String sql = String.format("DELETE FROM %s WHERE id = ?", tableName);
        try {
            LOGGER.info(sql + id);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            return statement.getUpdateCount();
        } catch (SQLException e) {
            String errorMessage = ERROR_SQL_MESSAGE;
            LOGGER.error(errorMessage + e.getMessage());
            throw new RuntimeException(errorMessage + e);
        }
    }

    protected abstract String getInsertFields();

    protected abstract String getInsertValues();

    protected abstract void setInsertStatementValues(PreparedStatement statement, T model);

    protected abstract String getUpdateFields();

    protected abstract void setUpdateStatementValues(PreparedStatement statement, T model);

    protected abstract int getUpdateStatementValuesCount();

    protected abstract long getId(T model);

    protected void validateObject(T model) {
        if (model == null) {
            LOGGER.error("Model cannot be null");
        }
    }

}
