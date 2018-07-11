package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password) throws Exception {

        userService.createUser(username,password);

        return "User '"+username+"' has been created!";
    }
}