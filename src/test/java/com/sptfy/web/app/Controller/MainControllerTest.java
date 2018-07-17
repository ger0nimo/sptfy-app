package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Exception.BusinessException;
import com.sptfy.web.app.Exception.ControllerExceptionHandler;
import com.sptfy.web.app.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    UserService userService;

    MockMvc mockMvc;

    MainController mainController;

    @Before
    public void setUp(){

        mainController = new MainController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test
    public void getUserData_ifAnyUserIsAuthenticated_returnUserData() throws Exception {

        Map<String, Object> user = new HashMap<>();
        user.put("username","user");
        user.put("userRole","ROLE_USER");
        user.put("registrationDate","11:30:59 29-11-2013");
        user.put("isPremium",true);

        when(mainController.getUserData()).thenReturn(user);

        mockMvc.perform(get(MainController.INDEX_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"registrationDate\":\"11:30:59 29-11-2013\",\"isPremium\":true,\"userRole\":\"ROLE_USER\",\"username\":\"user\"}"));

        assertEquals(user,mainController.getUserData());
    }

    @Test
    public void getUserData_ifNoUserIsAuthenticated_throwBusinessException() throws Exception{

        when(mainController.getUserData()).thenThrow(new BusinessException("No user has been authenticated!"));

        mockMvc.perform(get(MainController.INDEX_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.['Status Code']", equalTo(500)))
                .andExpect(jsonPath("$.Status", equalTo("INTERNAL_SERVER_ERROR")))
                .andExpect(jsonPath("$.Message", equalTo("No user has been authenticated!")));
    }

    @Test
    public void createUser_ifParamsArePassedInQuery_userIsAddedToDataBase() throws Exception {

        String username = "user";
        String password = "password";
        String returnMessage = "User '" + username + "' has been created!";

        mockMvc.perform(post(MainController.REGISTRATION_ENDPOINT)
               .contentType(MediaType.TEXT_PLAIN)
               .param("username",username)
               .param("password",password))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(returnMessage));

        verify(userService,times(1)).createUser(username,password);
    }

    @Test
    public void createUser_ifParamsAreNotPassedInQuery_400IsReturned() throws Exception {

        mockMvc.perform(post(MainController.REGISTRATION_ENDPOINT)
                .contentType(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}