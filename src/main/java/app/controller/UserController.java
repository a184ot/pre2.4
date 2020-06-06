package app.controller;

import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    private String listUsers(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/user")
    private String listUsersPost(ModelMap model) {
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }

    @PostMapping("/update")
    private String editUserForm(@ModelAttribute("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/updateUser")
    private String editedUsersSave(@ModelAttribute("id") Long id,
                                   @ModelAttribute("viewName") String viewName,
                                   @ModelAttribute("login") String login,
                                   @ModelAttribute("password") String password,
                                   @ModelAttribute("email") String email,
                                   @ModelAttribute("age") int age,
                                   @ModelAttribute("role") String role, ModelMap model) {
        User user = new User(id, viewName, login, password, email, age);
        String[] roles = {role};
        userService.editUser(user,roles);
        List<User> userList = userService.listAllUsers();
        model.addAttribute("userList", userList);
        return "all-users";
    }



}
