package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    boolean add(User user);
    boolean deleteUser(Long id);
    void editUser(User user);
    List<User> listAllUsers();
    User getUserByLogin(String login);
    User getUserById(Long id);
}
