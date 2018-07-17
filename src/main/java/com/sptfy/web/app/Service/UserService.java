package com.sptfy.web.app.Service;

import com.sptfy.web.app.Exception.BusinessException;
import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import com.sptfy.web.app.Utils.DateFormater;
import io.swagger.annotations.Authorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

//    public UserService() {
//    } EMPTY CONSTRUCTOR - NULL POINTER EXCEPTION!!!!!!!!!!!

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password) throws Exception {

        String hashedPassword = passwordEncoder.encode(password);
        User existingUser = userRepository.findByUsername(username);

        System.out.println(existingUser);

        if (existingUser == null) {
            User user = new User(username, hashedPassword, "ROLE_USER", DateFormater.getCurrentDate(), true, true, true, true, false); // HAS TO BE "ROLE_XYZ" HERE, e.g."ROLE_USER"
            userRepository.save(user);

        } else {
            throw new BusinessException("User '" + username + "' already exists!");
        }
    }

    public Map<String, Object> getUserData() throws BusinessException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        if (currentUsername == null) throw new BusinessException("No user has been authenticated!");
        System.out.println(currentUsername);

        User user = userRepository.findByUsername(currentUsername);

        Map<String, Object> userData = new HashMap<>();

        //DTO instead of this???
        userData.put("username", user.getUsername());
        userData.put("userRole", user.getRole());
        userData.put("registrationDate", user.getRegistrationDate());
        userData.put("isPremium", user.isPremium());
        //TODO add other user data + above comment?

        return userData;
    }
}