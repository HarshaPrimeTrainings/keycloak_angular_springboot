package com.harshatrainings.prodcutservice.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class JwtAuthConvertor implements Converter<Jwt, AbstractAuthenticationToken>{

	@Value("${spring.security.oauth2.client.registration.keycloak.client-id}")
	String clientId;
	
	private final JwtGrantedAuthoritiesConverter jwtGrantAuthConvertor = new JwtGrantedAuthoritiesConverter();

	@Override
	public AbstractAuthenticationToken convert(Jwt jwttoken) {
		//Stream.concat(jwtGrantAuthConvertor.convert(jwttoken).stream())
		return new JwtAuthenticationToken(jwttoken,extractRoles(jwttoken));
	}
	
	
	private Collection<? extends GrantedAuthority> extractRoles(Jwt jwttoken){
		
		Set<String> roles = new HashSet<>();
		
		Map<String, Object> realmAccess =  jwttoken.getClaim("realm_access");
		System.out.print("RealmAccess"+ realmAccess);
		
		if(realmAccess!=null && realmAccess.containsKey("roles")) {
			roles.addAll((Collection<? extends String>) realmAccess.get("roles"));
		}
		
		Map<String, Object> reaourceAccess =jwttoken.getClaim("resource_access");
		
		System.out.println("Reource ACCESS :: "+ reaourceAccess);
		
		if(reaourceAccess!=null && reaourceAccess.containsKey(clientId)) {
			 Map< String, Object> accesClient= (Map<String, Object>) reaourceAccess.get(clientId);
			 if(accesClient!=null && accesClient.containsKey("roles")) {
				 roles.addAll((Collection<? extends String>) accesClient.get("roles"));
			 }
		}
		
		return roles.stream().map(role->new SimpleGrantedAuthority("ROLE_"+role.toUpperCase())).collect(Collectors.toSet());
		
	}
	
	
	
}
