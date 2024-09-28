package telran.edutrek.accounting.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class AuthorizationConfiguration
{
	@Autowired
	CustomWebSecurity customWebSecurity ;
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception
	{
		http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
		.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
		
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.POST, "/auth/account", "/auth/account/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/auth").authenticated()
				.requestMatchers(HttpMethod.DELETE, "/auth/{login}")
				.access(new WebExpressionAuthorizationManager("#login == authentication.name or hasRole('ADMIN')"))
				.anyRequest().denyAll());
		
		return http.build();
	}
}
