package com.sptfy.web.app.Bootstrap;

import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public DevBootstrap(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //initData();
    }

    private void initData(){

        User user1 = new User("user", passwordEncoder.encode("123"),"USER");

        userRepository.save(user1);
    }
}