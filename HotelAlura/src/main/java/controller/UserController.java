package controller;

import dao.UserDao;
import factory.ConnectionFactory;
import model.User;
import utilities.JOptionPane.UserShowMessages;

import java.util.List;

/**
 * The controller class for managing user operations.
 * This class acts as an intermediary between the user interface and the data access layer (DAO)
 * for performing CRUD (Create, Read, Update, Delete) operations on user entities.
 */
public class UserController {

    /**
     * The data access object for user operations.
     */
    private final UserDao userDao;

    /**
     * Constructs a new instance of the UserController.
     * Initializes the UserDao using a database connection from the ConnectionFactory.
     */
    public UserController() {
        this.userDao = new UserDao(ConnectionFactory.getConnection());
    }

    /**
     * Retrieves a list of all users stored in the system.
     *
     * @return A list of User objects representing all users.
     */
    public List<User> list() {
        return userDao.getAll();
    }

    /**
     * Retrieves a list of users based on the provided user category.
     *
     * @param userCategory The category of users to search for.
     * @return A list of User objects matching the provided user category.
     */
    public List<User> list(String userCategory) {
        return userDao.list(userCategory);
    }

    /**
     * Retrieves a list of users based on the provided username and password.
     *
     * @param userName     The username to search for.
     * @param userPassword The user password to search for.
     * @return A list of User objects matching the provided username and password.
     */
    public List<User> list(String userName, String userPassword) {
        return userDao.list(userName, userPassword);
    }

    /**
     * Saves a new user into the system.
     * Checks for duplicate usernames before inserting the user.
     *
     * @param user The User object representing the new user.
     * @throws IllegalArgumentException If the username is already taken.
     */
    public void save(User user) throws IllegalArgumentException {
        List<User> users = list();

        boolean isUsernameTaken = users.stream().anyMatch(user1 -> user1.getName().equals(user.getName()));

        if (isUsernameTaken) {
            UserShowMessages.showErrorMessage(
                    "User use",
                    "Please select another user name"
            );
            throw new IllegalArgumentException();
        } else {
            userDao.insert(user);
        }
    }

    /**
     * Updates user information in the system.
     *
     * @param user The User object representing the updated user information.
     * @return The number of affected rows in the database after the update operation.
     */
    public Integer update(User user) {
        return userDao.update(user);
    }

    /**
     * Deletes a user from the system based on the provided user ID.
     *
     * @param id The ID of the user to delete.
     * @return The number of affected rows in the database after the delete operation.
     */
    public Integer delete(Integer id) {
        return userDao.delete(id);
    }
}
