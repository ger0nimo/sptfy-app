//package com.sptfy.web.app.Utils;
//
//import com.sptfy.web.app.Models.Users;
//import com.sptfy.web.app.Repositories.UsersRepository;
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
//    private UsersRepository userRepository;
//
//    public UserDetailsServiceImpl(UsersRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Users> user = this.userRepository.findByUsername(username);
//        if (!user.isPresent())
//            throw new UsernameNotFoundException("Users does not exists!");
//        return new Users(user.getId(), user.getUsername(), user.get().getPassword(), user.get().getRole());
//    }
//
//
//}
