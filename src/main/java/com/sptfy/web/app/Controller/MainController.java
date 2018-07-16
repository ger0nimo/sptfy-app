package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Service.UserDetailsServiceImpl;
import com.sptfy.web.app.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@RequestMapping("/user")
public class MainController {

    public static final String INDEX_ENDPOINT = "/";
    public static final String REGISTRATION_ENDPOINT = "/join";

    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(INDEX_ENDPOINT)
    public Map<String,Object> getUserData() throws Exception {

        return userService.getUserData();
    }

    @PostMapping(REGISTRATION_ENDPOINT)
    public String createUser(@RequestParam String username, @RequestParam String password) throws Exception {

        userService.createUser(username,password);

        return "User '"+username+"' has been created!";
    }
}