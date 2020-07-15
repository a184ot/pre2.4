package app.service;

import app.model.Role;
import app.model.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();
    Role getRoleByName(String roleName);
    void addRole(Role role);
    void deleteRole(Long id);
    void editRole(Role role);
    Role getRoleById(Long id);
    User getUserRolesByRolesId(User user, Long[] roles);
}