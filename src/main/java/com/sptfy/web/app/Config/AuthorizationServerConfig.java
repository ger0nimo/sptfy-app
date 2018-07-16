package com.sptfy.web.app.Config;

import com.sptfy.web.app.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    public static final String PASS_CLIENT_ID = "passClientId";
    public static final String IMPL_CLIENT_ID = "implClientId";
    public static final String CLIENT_SECRET = "webSecret";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

//    @Value("classpath:sqlschema.file")
//    private Resource schemaScript;

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private Integer tokenValidity = 60*60;

    private Integer refreshTokenValidity = 3600;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(
            AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        //password flow
        clients.inMemory() //memory
//          clients.jdbc(dataSource) //db
                .withClient(this.PASS_CLIENT_ID)
                .secret(this.passwordEncoder.encode(this.CLIENT_SECRET))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .accessTokenValiditySeconds(this.tokenValidity)
                .refreshTokenValiditySeconds(this.refreshTokenValidity)
                .scopes("read", "write");
//////              .and().build();??

//        implicit flow in memory
//        clients.inMemory() //memory
////        clients.jdbc(dataSource) //db
//                .withClient(this.IMPL_CLIENT_ID)
//                .authorizedGrantTypes("implicit", "refresh_token")
//                .scopes("read")
//                .accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(2592000)
//                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

    @Bean
    public TokenStore tokenStore() {

        return new JdbcTokenStore(dataSource);
    }

//    @Bean
//    protected AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }
}
