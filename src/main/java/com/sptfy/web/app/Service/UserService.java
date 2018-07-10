package com.sptfy.web.app.Service;

import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password){

        String hashedPassword = passwordEncoder.encode(password);

        System.out.println("BEFORE HASH: "+password);
        System.out.println("AFTER HASH: "+ hashedPassword);

        User user = new User(username,hashedPassword,"USER");

        userRepository.save(user);
    }
}
