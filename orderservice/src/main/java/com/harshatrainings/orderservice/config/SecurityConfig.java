package com.harshatrainings.orderservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthConvertor jwtConvertor;
	
	
	
	@Bean
	SecurityFilterChain initSecurityConfig(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize->authorize
				.requestMatchers("/order/**").hasRole("ADMIN")
				.anyRequest().authenticated())
		.oauth2ResourceServer(oauth->oauth.jwt(jwt->jwt.jwtAuthenticationConverter(jwtConvertor)));
		return http.build();
		
	}
	
}
