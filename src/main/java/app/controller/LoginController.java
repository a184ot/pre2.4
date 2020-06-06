package app.controller;

import app.model.User;
import app.service.UserService;
import org.apache.log4j.Logger;
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

//    @PostConstruct
//    private void firstUser() {
//        userService.add(new User("admin","admin", "123456","q@qq.q",33),
//                new String[]{"ADMIN"});
//    }
}
