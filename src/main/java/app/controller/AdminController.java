package app.controller;

import app.model.User;
import app.service.RoleService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    RoleService roleService;


    @GetMapping("/admin")
    private String userList(Model model) {
        model.addAttribute("userList", userService.listAllUsers());
        return "all-users";
    }

    @PostMapping({"delete","admin/delete"})
    private String  deleteUser(@ModelAttribute("id") Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("userList", userService.listAllUsers());
        return "all-users";
    }


    @PostMapping({"/update","admin/update"})
    private String editUserForm(@ModelAttribute("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("rolesuser", roleService.getRoles());
        return "update";
    }

    @PostMapping(value = "/updateUser")
    private String updateUser (User user, String[] role) {
        userService.editUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping({"/create","admin/create"})
    private String createUserPage(ModelMap modelMap) {
        return "create";
    }

    @PostMapping({"/createUser", "admin/createUser"})
    private String createNewUser(User user) {
        String[] roles = {"ROLE_USER"};
        userService.add(user,roles);
        return "redirect:/admin";
    }

}
