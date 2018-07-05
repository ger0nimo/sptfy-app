package com.sptfy.web.app.Service;

import com.sptfy.web.app.Model.Users;
import com.sptfy.web.app.Repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository userRepository;

//    private String userRole;

    public UserDetailsServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }


    private List<GrantedAuthority> getAuthoritiesList(Users users){

        List<GrantedAuthority> authoritiesList = new ArrayList<>();

            authoritiesList.add(new SimpleGrantedAuthority(users.getRole()));

            return authoritiesList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userRepository.findByUsername(username);
//        userRole = user.getRole();
        if (user == null) {
            throw new UsernameNotFoundException(username+" does not exist!");
        }
        return new User(user.getUsername(),user.getPassword(),true,true,true,true, getAuthoritiesList(user));
    }

}
