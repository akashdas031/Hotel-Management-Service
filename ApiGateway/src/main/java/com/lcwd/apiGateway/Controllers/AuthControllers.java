package com.lcwd.apiGateway.Controllers;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.apiGateway.Models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthControllers {

	private Logger logger=LoggerFactory.getLogger(AuthControllers.class);
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			){
		logger.info("user email id : "+user.getEmail());
		//creating auth response object
		AuthResponse authResponse = new AuthResponse();
		//setting email to auth response
		authResponse.setUserId(user.getEmail());
		//setting access token to auth response
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		//setting refresh token to the authResponse
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
		//setting expires at to authResponse
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		//setting authorities to authResponse
		
		List<String> authorities = user.getAuthorities().stream().map(grantedAuthorities->{
		return grantedAuthorities.getAuthority();
		}).collect(Collectors.toList());
		
		authResponse.setAuthorities(authorities);
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
	}
}
