package com.lcwd.hotelService.Configurations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import com.lcwd.hotelService.Configurations.Interceptors.RestTemplateInterceptor;

@Configuration
public class hotelConfig {

	private OAuth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
	
	private ClientRegistrationRepository clientRegistrationRepository;
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptor=new ArrayList<>();
		interceptor.add(new RestTemplateInterceptor(manager(oauth2AuthorizedClientRepository,clientRegistrationRepository)));
		return restTemplate;
	}
	
	@Bean
	public OAuth2AuthorizedClientManager manager(OAuth2AuthorizedClientRepository oauth2AuthorizationClientRepository,
			ClientRegistrationRepository clientRegistrationRepository) {
		OAuth2AuthorizedClientProvider provider=OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
		DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager=new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, oauth2AuthorizationClientRepository);
		defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
		return defaultOAuth2AuthorizedClientManager;
	}
}
