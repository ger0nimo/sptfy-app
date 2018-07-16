package com.sptfy.web.app.Config;

import com.sptfy.web.app.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //User from memory
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder.encode("123")).roles("USER");
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder.encode("123")).roles("USER", "ADMIN");

        // User from DB
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http
//                .authorizeRequests()
//                .antMatchers("/h2-console").permitAll()
//                .antMatchers("/*").authenticated() //order of these two is important
////                .anyRequest().hasRole("USER")                               // !!!
////                .and()
////                .formLogin()
//////                .and()
//////                .httpBasic()
//                .and()
//                .csrf().disable()                       //these two mandatory to enable
//                .headers().frameOptions().disable();    //H2 Console when Spring Security is configured


        //If Resource Server Config is configured here below code is sufficient. Otherwise code above or code from ResourceServerConfig is required
        http
                .authorizeRequests()
                .anyRequest()
                .denyAll()
                .and()
                .formLogin()
                .disable();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}