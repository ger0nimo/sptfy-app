package com.sptfy.web.app.Service;

import com.sptfy.web.app.Exception.BusinessException;
import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import com.sptfy.web.app.Utils.DateFormater;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    PasswordEncoder passwordEncoder;

    UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password) throws Exception {

        String hashedPassword = passwordEncoder.encode(password);

        User user = userRepository.findByUsername(username);

        if(user == null){

            User user2 = new User(username,hashedPassword,"ROLE_USER",DateFormater.getCurrentDate(),true,true,true,true,false); // HAS TO BE "ROLE_XYZ" HERE, e.g."ROLE_USER"
            userRepository.save(user2);

        } else{
            throw new BusinessException("User '"+username+"' already exists!");
        }
    }

    public Map<String,Object> getUserData(String username){

        User user = userRepository.findByUsername(username);
        System.out.println(user.getUsername());

        Map<String, Object> userData = new HashMap<>();

        userData.put("username",user.getUsername());
        userData.put("userRole",user.getRole());
        userData.put("registrationDate",user.getRegistrationDate());
        userData.put("isPremium",user.isPremium());
        //TODO add other user data


        return userData;
    }


}