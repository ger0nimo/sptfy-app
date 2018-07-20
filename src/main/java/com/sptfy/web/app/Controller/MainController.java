package com.sptfy.web.app.Controller;


import com.sptfy.web.app.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;

import java.util.Map;

@RestController
public class MainController {

    public static final String INDEX_URL = "/";
    public static final String REGISTRATION_URL = "/join";

    UserService userService;
    User authenticatedUser;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(INDEX_URL)
    public Map<String, Object> getUserData(@AuthenticationPrincipal User user) throws Exception {

        return userService.getUserData(user.getUsername());
    }

    @PostMapping(REGISTRATION_URL)
    public String createUser(@RequestParam String username, @RequestParam String password) throws Exception {

        userService.createUser(username, password);

        return "User '" + username + "' has been created!";
    }

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
}