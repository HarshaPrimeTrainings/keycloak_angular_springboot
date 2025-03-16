package com.harshatrainings.prodcutservice.config;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	RestTemplate initRestTemplate(RestTemplateBuilder restBuilder) {
		RestTemplate resttemplate = restBuilder.build();
		resttemplate.setInterceptors(List.of(new RestJwtInterceptor()));
		return resttemplate;
	}

}
