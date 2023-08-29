package controller;

import dao.UserDao;
import factory.ConnectionFactory;
import model.User;
import utilities.JOptionPane.UserShowMessages;

import java.util.List;

public class UserController {

    private final UserDao userDao;

    public UserController() {
        this.userDao = new UserDao(ConnectionFactory.getConnection());
    }

    public List<User> list() {
        return userDao.getAll();
    }

    public List<User> list(String userCategory) {
        return userDao.list(userCategory);
    }

    public List<User> list(String userName, String userPassword) {
        return userDao.list(userName, userPassword);
    }

    public void save(User user) {
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

    public Integer update(User user) {
        return userDao.update(user);
    }

    public Integer delete(Integer id) {
        return userDao.delete(id);
    }
}
