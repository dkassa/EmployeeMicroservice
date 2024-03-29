package com.EMicroserviceProject.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Securityconfiig {

	
	//Auth
	@Bean
	public UserDetailsService detailService(PasswordEncoder encoder) {
		
		UserDetails admin=User.withUsername("desalegn").password(encoder.encode("123456"))
				.roles("ADMIN").build();
		

		UserDetails user=User.withUsername("alex").password(encoder.encode("1234567"))
				.roles("USER").build();
		
		return new InMemoryUserDetailsManager(admin,user);
		
		
	}
	
	//Author
	
	//passencode
	@Bean
	public PasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		return 
				http.csrf().disable().authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/api/vi/employee")).permitAll()
				.requestMatchers(new AntPathRequestMatcher("/api/vi/**")).authenticated()
				.and().httpBasic().and().build();
	}
}
