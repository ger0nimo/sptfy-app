package com.sptfy.web.app.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


}

//http://www.baeldung.com/rest-api-spring-oauth2-angularjs
//https://dzone.com/articles/secure-spring-rest-with-spring-security-and-oauth2
