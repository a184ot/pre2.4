package app.controller;

import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import app.service.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {

    private UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    private String listUsers(ModelMap model) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUserByLogin(userName);
        List<User> listUser = new ArrayList<>();
        listUser.add(user);
        model.addAttribute("role", "USER");
        model.addAttribute("userList", listUser);
        return "userPage";
    }
}
