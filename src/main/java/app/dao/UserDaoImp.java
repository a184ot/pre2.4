package app.dao;

import app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

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
            User user = entityManager.createQuery(
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
