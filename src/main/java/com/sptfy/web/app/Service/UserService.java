package com.sptfy.web.app.Service;

import com.sptfy.web.app.Exception.BusinessException;
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

    public void createUser(String username, String password) throws Exception {

        String hashedPassword = passwordEncoder.encode(password);

        User user = userRepository.findByUsername(username);

        if(user == null){

            User user2 = new User(username,hashedPassword,"ROLE_USER"); // HAS TO BE "ROLE_[DEFINED ROLE, e.g. USER]"
            userRepository.save(user2);

        } else{

            throw new BusinessException("User '"+username+"' already exist!");
        }
    }
}
