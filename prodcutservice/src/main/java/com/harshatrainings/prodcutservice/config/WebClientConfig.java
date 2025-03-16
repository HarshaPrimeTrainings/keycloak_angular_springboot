package com.harshatrainings.prodcutservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	WebClient initWebClient(WebClient.Builder builder) {
			
		return builder.filter((request,next)->{
			Authentication authenticaiton= SecurityContextHolder.getContext().getAuthentication();
			Jwt jwt = (Jwt) authenticaiton.getPrincipal();
			String token = jwt.getTokenValue();
			return next.exchange(ClientRequest.from(request)
					.headers(headers->headers.setBearerAuth(token)).build());
			
		}).build();
	}
	
	
}
