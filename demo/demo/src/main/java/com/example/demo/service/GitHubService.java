package com.example.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class GitHubService {

    private static final String GITHUB_USER_API_URL = "https://api.github.com/user";
    
    private final OAuth2AuthorizedClientService authorizedClientService;

    public GitHubService(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    public String getGitHubUserName(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            OAuth2User oauth2User = oauthToken.getPrincipal();

            // Extract user name from the OAuth2User attributes
            return (String) oauth2User.getAttributes().get("name");
        }
        return "Anonymous";
    }

}