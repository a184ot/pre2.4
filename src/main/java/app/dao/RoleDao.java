package app.dao;

import app.model.Role;
import app.model.User;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRole();
    Role getRoleByName(String roleName);
    void addRole(Role role);
    void deleteRole(Long id);
    void editRole(Role role);
    Role getRoleById(Long id);
}
