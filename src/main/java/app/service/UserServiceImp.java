package app.service;

import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public boolean add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return true;
    }

    @Transactional
    @Override
    public void editUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.editUser(user);
    }

    @Transactional
    @Override
    public boolean deleteUser(Long id) {
        if (userDao.getUserById(id).isEnabled()) {
            userDao.deleteUser(id);
            return true;
        }
        return false;
    }

    @Override
    public List<User> listAllUsers() {
        return userDao.listAllUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
}
