package app.service;

import app.dao.RoleDao;
import app.dao.UserDao;
import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public boolean add(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            Role roleDB = roleService.getRoleByName(role);
            roleSet.add(roleDB);
        }
        user.setRole(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        return true;
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

    @Transactional
    @Override
    public void editUser(User user, String[] roles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            Role roleDB = roleService.getRoleByName(role);
            roleSet.add(roleDB);
        }
        user.setRole(roleSet);
        userDao.editUser(user);
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

/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> usergtList(Long idMin) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId",idMin).getResultList();
    }*/
}
