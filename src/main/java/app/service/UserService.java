package app.service;

import app.model.User;

import java.util.List;

public interface UserService {
    boolean add(User user, String[] roles);
    boolean deleteUser(Long id);
    void editUser(User user, String[] roles);
    List<User> listAllUsers();
    User getUserByLogin(String login);
    User getUserById(Long id);
}
