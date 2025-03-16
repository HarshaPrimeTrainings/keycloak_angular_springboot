package com.harshatrainings.orderservice.config;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class RestJwtInterceptor implements ClientHttpRequestInterceptor{

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String token = null;
		Authentication authenticaiton= SecurityContextHolder.getContext().getAuthentication();
		if(authenticaiton!=null && authenticaiton.getPrincipal() instanceof Jwt) {
			Jwt jwt = (Jwt) authenticaiton.getPrincipal();
			token = jwt.getTokenValue();
			request.getHeaders().set(HttpHeaders.AUTHORIZATION, token);
		}
		
		return execution.execute(request, body);
	}

	
}
