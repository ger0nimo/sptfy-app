package com.sptfy.web.app.Controller;

import com.sptfy.web.app.Exception.ControllerExceptionHandler;
import com.sptfy.web.app.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerTest {

    @Mock
    UserService userService;

    @Autowired
    WebApplicationContext webApplicationContext;

    MockMvc mockMvc;

    MainController mainController;

    @Before
    public void setUp() {

        mainController = new MainController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).setControllerAdvice(new ControllerExceptionHandler()).build();
    }

    @Test //TODO ogarnac testowanie z @AuthenticationPrincipal
    public void getUserData_ifAnyUserIsAuthenticated_returnUserData() throws Exception {

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        mockMvc = MockMvcBuilders.webApplicationContextSetup(webApplicationContext).build();

        String username = "user";
        String password = "pass";
        String role = "ROLE_USER";

        User user = new User(username, password, AuthorityUtils.createAuthorityList(role));

        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

//        Map<String, Object> user = new HashMap<>();
//        user.put("username", "user");
//        user.put("userRole", "ROLE_USER");
//        user.put("registrationDate", "11:30:59 29-11-2013");
//        user.put("isPremium", true);

        //when(mainController.getUserData(authenticatedUser)).thenReturn(user);

        mockMvc.perform(get(MainController.INDEX_URL)
                .contentType(MediaType.APPLICATION_JSON)
                //.param("user", authUser))
                .principal(testingAuthenticationToken))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("{\"registrationDate\":\"11:30:59 29-11-2013\",\"isPremium\":true,\"userRole\":\"ROLE_USER\",\"username\":\"user\"}"));

//        assertEquals(user, mainController.getUserData(authenticatedUser));
    }

    @Test
    public void getUserData_ifNoUserIsAuthenticated_throwBusinessException() throws Exception{
//
//        when(mainController.getUserData()).thenThrow(new BusinessException("No user has been authenticated!"));
//
//        mockMvc.perform(get(MainController.INDEX_URL)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().is5xxServerError())
//                .andExpect(jsonPath("$.['Status Code']", equalTo(500)))
//                .andExpect(jsonPath("$.Status", equalTo("INTERNAL_SERVER_ERROR")))
//                .andExpect(jsonPath("$.Message", equalTo("No user has been authenticated!")));
    }

    @Test
    public void createUser_ifParamsArePassedInQuery_userIsAddedToDataBase() throws Exception {

        String username = "user";
        String password = "password";
        String returnMessage = "User '" + username + "' has been created!";

        mockMvc.perform(post(MainController.REGISTRATION_URL)
                .contentType(MediaType.TEXT_PLAIN)
                .param("username", username)
                .param("password", password))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(returnMessage));

        verify(userService, times(1)).createUser(username, password);
    }

    @Test
    public void createUser_ifParamsAreNotPassedInQuery_400IsReturned() throws Exception {

        mockMvc.perform(post(MainController.REGISTRATION_URL)
                .contentType(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}