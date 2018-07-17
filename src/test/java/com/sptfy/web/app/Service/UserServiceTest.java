package com.sptfy.web.app.Service;

import com.sptfy.web.app.Exception.BusinessException;
import com.sptfy.web.app.Model.User;
import com.sptfy.web.app.Repository.UserRepository;
import com.sptfy.web.app.Utils.DateFormater;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    UserService userService;

    @Before
    public void setUp() {
        userService = new UserService(passwordEncoder, userRepository);
    }


    @Test
    public void createUser_ifUserNotExist_itIsAddedToDataBase() throws Exception {

        String username = "user";
        String password = "password";
        String hashedPassword = passwordEncoder.encode(password);
        String role = "ROLE_USER";

         User user = new User(username, hashedPassword, role, DateFormater.getCurrentDate(), true, true, true, true, false);

        userService.createUser(username,password);
        verify(userRepository, times(1)).save(user);
    }

    @Test(expected = BusinessException.class)
    public void createUser_ifUserExist_throwBusinessException() throws Exception {

        String username = "user";
        String password = "password";
        String hashedPassword = passwordEncoder.encode(password);
        String role = "ROLE_USER";

        User user = new User(username, hashedPassword, role, DateFormater.getCurrentDate(), true, true, true, true, false);

        when(userRepository.findByUsername(username)).thenReturn(user);
        userService.createUser(username,password);
    }

    @Test
    public void getUserData_ifUserIsAuthenticated_returnUserData(){

    }

    @Test//(expected = BusinessException.class)
    public void getUserData_ifUserIsNotAuthenticated_throwsBusinnesException() throws BusinessException {



    }
}