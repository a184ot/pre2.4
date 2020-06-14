package app.controller;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping(name = "/")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping(name = "/")
    private String Login(){
        return "login";
    }

    @PostConstruct
    private void firstUser() {
        User user = new User("admin2","admin2", "123456","q@qq.q",33);
        if (userService.getUserByLogin(user.getLogin())==null) {
            String role[] = new String[]{"ROLE_USER", "ROLE_ADMIN"};
            userService.add(user, role);
        }
    }
}
