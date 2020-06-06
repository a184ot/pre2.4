package app.dao;

import app.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao {
    List<Role> getRole();
    Role getRoleByName(String roleName);
}
