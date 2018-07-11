package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import com.sptfy.web.app.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@RequestParam String username, @RequestParam String password){

        userService.createUser(username,password);

        return "User "+username+" has been created!";
    }

    @GetMapping("/get")
    public String getUser(@RequestParam String username){


        User user = userRepository.findByUsername(username);




        return "USER: "+user.getId()+", "+user.getUsername()+", "+user.getPassword()+", "+user.getRole();
    }
}