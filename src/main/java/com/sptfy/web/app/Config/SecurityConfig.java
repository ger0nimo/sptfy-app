package com.sptfy.web.app.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("123")).roles("USER");
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder.encode("123")).roles("USER", "ADMIN");

    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

////        http.addFilterBefore(this.corsFilter, ChannelProcessingFilter.class);
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic()
//                .and()
//                .headers().frameOptions().disable();
////                .anyRequest()
////                .denyAll()
////                .and()
////                .formLogin()
////                .disable();

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
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(authorizationServerConfig.dataSource());
//    }


}