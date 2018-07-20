package com.sptfy.web.app.Service;

import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import com.sptfy.web.app.Utils.DateFormater;
import com.sptfy.web.app.Utils.UserData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @Mock
    UserRepository userRepository;

    UserDetailsServiceImpl userDetailsService;

    @Before
    public void setUp() {
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUserName(){

        String username = "user";
        String hashedPassword = "password";
        String role = "USER_ROLE";
        List<GrantedAuthority> authorities = new ArrayList<>();
        UserData authenticatedUser = new UserData(username,hashedPassword,true,true,true,true,authorities);
        User user = new User(username, hashedPassword, role, DateFormater.getCurrentDate(), true, true, true, true, false);

        when(userRepository.findByUsername(username)).thenReturn(user);
        userDetailsService.loadUserByUsername(username);
        assertEquals(authenticatedUser,userDetailsService.loadUserByUsername(username));
    }
}