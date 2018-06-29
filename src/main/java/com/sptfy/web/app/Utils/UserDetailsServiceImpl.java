//package com.sptfy.web.app.Utils;
//
//import com.sptfy.web.app.Models.User;
//import com.sptfy.web.app.Repositories.UserRepository;
//import org.springframework.context.annotation.Role;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = this.userRepository.findByUsername(username);
//        if (!user.isPresent())
//            throw new UsernameNotFoundException("User does not exists!");
//        return new User(user.getId(), user.getUsername(), user.get().getPassword(), user.get().getRole());
//    }
//
//
//}
