package com.lcwd.MenuService.ConfigClasses.Interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
@Component
public class feignClientInterceptor implements RequestInterceptor{

	
	private OAuth2AuthorizedClientManager manager;
	
	@Override
	public void apply(RequestTemplate template) {
		String token = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("menu-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
		template.header("Authorization", "Bearer "+token);
		
	}

	
}
