package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/user")
public class MainController {

    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public Map<String,Object> getUserData(@RequestParam String username) throws Exception {


        return userService.getUserData(username);
    }


    @PostMapping("/join")
    public String createUser(@RequestParam String username, @RequestParam String password) throws Exception {

        userService.createUser(username,password);

        return "User '"+username+"' has been created!";
    }

}