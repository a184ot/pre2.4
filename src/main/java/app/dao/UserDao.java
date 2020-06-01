package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    void deleteUser(Long id);
    void editUser(User user);
    List<User> listAllUsers();
    User getUserByLogin(String login);
    User getUserById(Long id);
}
