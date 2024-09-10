package com.lcwd.hotelService.Configurations.Interceptors;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import feign.RequestInterceptor;

public class RestTemplateInterceptor implements ClientHttpRequestInterceptor{

	private OAuth2AuthorizedClientManager manager;
	
	
	
	public RestTemplateInterceptor(OAuth2AuthorizedClientManager manager) {
		super();
		this.manager = manager;
	}



	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		String accessToken = manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("hotel-internal-client").principal("internal").build()).getAccessToken().getTokenValue();
		request.getHeaders().add("Authorization", "Bearer "+accessToken);
		return execution.execute(request, body);
	}

}
