package com.sptfy.web.app.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    private String passClientId = "passClientId";
    private String implClientId = "implClientId";

    private String clientSecret = "webSecret";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

//    @Autowired
//    UserDetailsService userDetailsService;

//    @Value("classpath:sqlschema.file")
//    private Resource schemaScript;


    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    private Integer tokenValidity = 120;

    private Integer refreshTokenValidity = 3600;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    UserDetailsService userDetailsService;


    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


//        //password and implicit flows in db
//        clients.jdbc(dataSource)
////////                .withClient(this.implClientId)
////////                .authorizedGrantTypes("implicit")
////////                .scopes("read")
////////                .autoApprove(true)
////////                .and()
//                .withClient(this.passClientId)
//                .secret(this.passwordEncoder.encode(this.clientSecret))
//                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//                .accessTokenValiditySeconds(this.tokenValidity)
//                .refreshTokenValiditySeconds(this.refreshTokenValidity)
//                .scopes("read", "write")
//                .and().build();


        //password flow in memory
        clients.inMemory()
                .withClient(this.passClientId)
                .secret(this.passwordEncoder.encode(this.clientSecret))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(this.tokenValidity)
                .refreshTokenValiditySeconds(this.refreshTokenValidity)
                .scopes("read", "write");

        //implicit flow in memory
//        clients.inMemory()
//                .withClient(this.implClientId)
//                .authorizedGrantTypes("implicit")
//                .scopes("read")
//                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(tokenStore())
                //.userDetailsService(userDetailsService)
                //.authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
