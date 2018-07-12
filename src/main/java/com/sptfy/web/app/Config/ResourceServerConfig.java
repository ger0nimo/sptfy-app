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
                .authorizeRequests()                                            //ORDER OF TWO BELOW IS IMPORTANT!
                        .antMatchers("/h2-console/**").permitAll() //normally this should strictly required authentication
                        .antMatchers("/join").permitAll()
                        //.antMatchers("/**").hasRole("USER")       // * - authentication required for everything after /rest, ** - everything after /rest including /rest//
                        //.antMatchers("/rest/*").authenticated()
                        .antMatchers("/**").hasRole("USER") // here "USER" in db "ROLE_USER"
//                        .and()
//                        .formLogin()
//                        .and()
//                        .httpBasic()
                        .and()
                .csrf().disable()                       //these two are mandatory to enable
                .headers().frameOptions().disable();    //H2 Console when Spring Security is configured
//                        .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
