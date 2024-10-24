package telran.edutrek.accounting.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class AuthorizationConfiguration {

	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable())
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(HttpMethod.POST, "/auth/account", "/auth/account/").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/auth/block/*", "/auth/activate/*").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/auth", "/students/add", "/group/create", "/contact/add").authenticated()
				.requestMatchers(HttpMethod.GET, "/auth", "/auth/id/*", "/auth/login/*",
						"/students/id/*", "/group/*", "/group", "/students/name/*", "/students", "/contact/all",
						"/contact/id/*", "/contact/all/*", "/group/name/*").authenticated()
				.requestMatchers(HttpMethod.PUT, "/auth/password/*", "/auth/login/*",
						"/group/update/*", "/group/add/*/*", "/group/move/*/*", "/group/archive/*/*", "/students/comment/*",
						"/students/payments/*", "/contact/update",
						"/students/reminder/*", "/students/update/*").authenticated()
				.requestMatchers(HttpMethod.DELETE, "/students/*", "/contact/*", "/group/*", "/group/student/*/*").authenticated()
				.requestMatchers(HttpMethod.DELETE, "/auth/{login}")
				.access(new WebExpressionAuthorizationManager("#login == authentication.name or hasRole('ADMIN')"))
				.anyRequest().denyAll());

		return http.build();
	}
}
