package app.dao;

import app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Role> getRole() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role getRoleByName(String roleName) {
        return entityManager.find(Role.class, roleName);
    }
}
