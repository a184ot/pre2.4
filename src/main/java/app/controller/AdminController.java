package app.controller;

import app.model.Role;
import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private UserService userService;

    private RoleService roleService;

    @Autowired
    private AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    private String userList(Model model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByLogin(userName);
        model.addAttribute("user", user);
        model.addAttribute("userList", userService.listAllUsers());
        model.addAttribute("rolesuser", roleService.getRoles());
        return "all-users";
    }

    @PostMapping("admin/delete")
    private String  deleteUser(@ModelAttribute("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


    @PostMapping("/admin/updateUser")
    private String updateUser (User user,Long[] roles) {
        roleService.getUserRolesByRolesId(user,roles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/createUser")
    private String createNewUser(User user, Long[] roles) {
        roleService.getUserRolesByRolesId(user,roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/error")
    private String error() {
        return "error";
    }

}
