package com.sptfy.web.app.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {

                http
                .authorizeRequests()
                .antMatchers("/h2-console/**").hasRole("ADMIN") //order of these two is important
                .anyRequest().hasRole("USER")                               // !!!
                .and()
                .formLogin()
//                .and()
//                .httpBasic()
                .and()
                .csrf().disable()                       //these two mandatory to enable
                .headers().frameOptions().disable();    //H2 Console when Spring Security is configured
//                .and()
//                .formLogin()
//                .disable();


    }
}
