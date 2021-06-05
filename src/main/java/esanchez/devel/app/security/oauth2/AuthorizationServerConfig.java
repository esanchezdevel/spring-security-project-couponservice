package esanchez.devel.app.security.oauth2;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * 
 * This will be the Authentication Server
 * It uses the AuthenticationManager that we have created as a @Bean in the Oauth2SecurityConfig file
 * Also we use the UserDetailsService that we made for use mysql
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	private static final String RESOURCE_ID = "couponservice";

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/*
		 * configuration for store the tokens in memory
		 */
		//endpoints.tokenStore(new InMemoryTokenStore()).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		
		/*
		 * configuration for store the tokens in database
		 * IMPORTANT: Is needed to create the tables with the queries that we have stored in this project, in the file 
		 * /src/main/resources/database_queries/oauth2_tokens_tables.sql 
		 */
		endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(authenticationManager).userDetailsService(userDetailsService);
	}
	
	/*
	 * the client configuration for the authorization server
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("couponClientApp") //the usernamme
			.secret(passwordEncoder.encode("9999")) //the password. it must be encoded
			.authorizedGrantTypes("password", "refresh_token")
			.scopes("read", "write")
			.resourceIds(RESOURCE_ID);
	}
}
