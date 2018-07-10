package com.sptfy.web.app.Service;

import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    private List<GrantedAuthority> getAuthoritiesList(User user){

        List<GrantedAuthority> authoritiesList = new ArrayList<>();

            authoritiesList.add(new SimpleGrantedAuthority(user.getRole()));

        System.out.println(user.getRole());

            return authoritiesList;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username+" does not exist!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true, getAuthoritiesList(user));
    }

}
