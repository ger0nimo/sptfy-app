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
                        .antMatchers("/h2-console").permitAll()
                        .antMatchers("/rest/**").authenticated() // * - authentication required for everything after /rest, ** - everything after /rest including /rest
                .and()
                .csrf().disable()                       //these two are mandatory to enable
                .headers().frameOptions().disable();    //H2 Console when Spring Security is configured
    }
}
