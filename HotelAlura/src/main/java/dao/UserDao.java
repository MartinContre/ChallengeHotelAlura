package dao;

import model.User;
import utilities.StringUtilities;
import utilities.enums.ColumnsKey;
import utilities.InsetFieldGenerator;
import utilities.enums.EmployeeCategory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends BaseDao<User> {
    private static final String INSERT_VALUES = "(?, ?, ?)";
    private static final int UPDATE_VALUES_COUNT = 4;
    private static final String[] USED_COLUMNS = {
            ColumnsKey.USER_NAME.getKey(), ColumnsKey.USER_CATEGORY.getKey(), ColumnsKey.PASSWORD.getKey()
    };

    public UserDao (Connection connection) {
        tableName = "users";
        this.connection = connection;
    }

    public List<User>  list(String userCategory) {
        List<User> result = new ArrayList<>();
        try {
            String sql = String.format("""
                            SELECT\s
                            %s, %s, %s
                            FROM %s
                            WHERE %s LIKE ?""",
                    ColumnsKey.ID.getKey(),
                    ColumnsKey.USER_NAME.getKey(),
                    ColumnsKey.USER_CATEGORY.getKey(),
                    tableName,
                    ColumnsKey.USER_CATEGORY.getKey());
            try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userCategory.concat("%"));
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    User row = new User(
                            resultSet.getInt(ColumnsKey.ID.getKey()),
                            resultSet.getString(ColumnsKey.USER_NAME.getKey()),
                            StringUtilities.convertUserCategoryStrToEmployeeCategory(resultSet.getString(ColumnsKey.USER_CATEGORY.getKey()))
                    );
                    result.add(row);
                }
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error("Error while retrieving data: " + e.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    "Try again.",
                    "Error while retrieving data.",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e);
        }
    }

    public List<User> list(String userName, String password) {
        List<User> result = new ArrayList<>();
        try {
            String sql = String.format(
                    "SELECT %s, %s FROM %s WHERE %s = ? AND %s = ?",
                    ColumnsKey.USER_NAME.getKey(),
                    ColumnsKey.USER_CATEGORY.getKey(),
                    tableName,
                    ColumnsKey.USER_NAME.getKey(),
                    ColumnsKey.PASSWORD
            );
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setString(1, userName);
                statement.setString(2, password);
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    User row = new User(
                            resultSet.getString(ColumnsKey.USER_NAME.getKey()),
                            StringUtilities.convertUserCategoryStrToEmployeeCategory(resultSet.getString(ColumnsKey.USER_CATEGORY.getKey()))
                    );
                    result.add(row);
                }
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_SQL_MESSAGE + e.getMessage());
            JOptionPane.showMessageDialog(
                    null,
                    "Try again, an error occurred while retrieving the data",
                    "Error while retrieving the data",
                    JOptionPane.ERROR_MESSAGE
            );
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected User createModel(ResultSet resultSet) {
        try {
            LOGGER.info("Creating model user");
            Integer id = resultSet.getInt(ColumnsKey.ID.getKey());
            String userName = resultSet.getString(ColumnsKey.USER_NAME.getKey());
            String userCategoryStr = resultSet.getString(ColumnsKey.USER_CATEGORY.getKey());
            EmployeeCategory userCategory = StringUtilities.convertUserCategoryStrToEmployeeCategory(userCategoryStr);
            String userPassword = resultSet.getString(ColumnsKey.PASSWORD.getKey());
            return new User(id, userName, userCategory, userPassword);
        } catch (SQLException e) {
            LOGGER.error("Couldn't create model " + e.getMessage());
            throw new RuntimeException("Couldn't create model " + e);
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
    protected void setInsertStatementValues(PreparedStatement statement, User user) {
        try {
            LOGGER.info("Setting insert statement values for guest model");
            statement.setString(1, user.getName());
            statement.setString(2, user.getCategory().toString());
            statement.setString(3, user.getPassword());
        } catch (SQLException e) {
            LOGGER.error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected String getUpdateFields() {
        return InsetFieldGenerator.generateUpdateFields(USED_COLUMNS);
    }

    @Override
    protected void setUpdateStatementValues(PreparedStatement statement, User user) {
        try {
            LOGGER.info("Setting insert statement values for guest model");
            statement.setString(1, user.getName());
            statement.setString(2, user.getCategory().toString());
            statement.setString(3, user.getPassword());
        } catch (SQLException e) {
            LOGGER.error("Couldn't set statement values: " + e.getMessage());
        }
    }

    @Override
    protected int getUpdateStatementValuesCount() {
        return UPDATE_VALUES_COUNT;
    }

    @Override
    protected long getId(User user) {
        return user.getId();
    }
}
