package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.SecondaryTable;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping(value = "")
    private String userList(Model model) {
        model.addAttribute("userList", userService.listAllUsers());
        return "all-users";
    }

    @PostMapping({"delete"})
    private String  deleteUser(@ModelAttribute("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping({"update"})
    private String editUserForm(@ModelAttribute("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("rolesuser", roleService.getRoles());
        return "update";
    }

    @PostMapping(value = "updateUser")
    private String updateUser (User user) {
        Set<Role> roles= new HashSet<>();
        for (Role role : user.getRole()) {
            roles.add(roleService.getRoleByName(role.getName()));
        }
        user.setRole(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping({"create"})
    private String createUserPage(ModelMap modelMap) {
        return "create";
    }

    @PostMapping({"createUser"})
    private String createNewUser(User user,Long[] rolesIds) {
        user.setRole(getRoleSetFromId(rolesIds));
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/error")
    private String error() {
        return "error";
    }

    private Set<Role> getRoleSetFromId(Long[] rolesIds) {
        Set<Role> roles= new HashSet<>();
        for (Long roleId: rolesIds) {
            roles.add(roleService.getRoles().get(roleId.intValue()));
        }
        return roles;
    }
}
