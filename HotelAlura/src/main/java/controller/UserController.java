package controller;

import dao.UserDao;
import factory.ConnectionFactory;
import model.User;

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
        userDao.insert(user);
    }

    public Integer update(User user) {
        return userDao.update(user);
    }

    public Integer delete(Integer id) {
        return userDao.delete(id);
    }
}
