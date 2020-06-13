package app.dao;

import app.model.User;
import app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    private RoleService roleService;

    @Autowired
    public UserDaoImp(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User getUserByLogin(String login) {
        try{
            User user = entityManager
                    .createQuery(
                    "SELECT u from User u WHERE u.login = :login", User.class).
                    setParameter("login", login).getSingleResult();
            return user;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public User findByUsername(String username) {
        return getUserByLogin(username);
    }

}
