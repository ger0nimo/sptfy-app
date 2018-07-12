//package com.sptfy.web.app.Bootstrap;
//
//import com.sptfy.web.app.Model.User;
//import com.sptfy.web.app.Repository.UserRepository;
//import com.sptfy.web.app.Utils.DateFormater;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
//
//    private PasswordEncoder passwordEncoder;
//
//    private UserRepository userRepository;
//
//    public DevBootstrap(PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
//
//
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        initData();
//    }
//
//    private void initData(){
//
//        User user1 = new User("admin", passwordEncoder.encode("admin"),"ROLE_ADMIN",DateFormater.getCurrentDate(),true,true,true,true,true);
//
//        userRepository.save(user1);
//    }
//}