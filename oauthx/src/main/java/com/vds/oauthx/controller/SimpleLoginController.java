package com.vds.oauthx.controller;

import com.vds.oauthx.payload.general.GeneralResponse;
import com.vds.oauthx.payload.general.ResponseFactory;
import com.vds.oauthx.payload.request.OAuth2GithubRequest;
import com.vds.oauthx.payload.request.OAuth2GoogleRequest;
import com.vds.oauthx.payload.response.OAuth2GithubResponse;
import com.vds.oauthx.payload.response.OAuth2GoogleResponse;
import com.vds.oauthx.payload.response.OAuth2InfoDTO;
import com.vds.oauthx.payload.response.OAuth2LoginInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
public class SimpleLoginController {
    private final RestTemplate restTemplate;

    public SimpleLoginController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/oauth2/google")
    public ResponseEntity<GeneralResponse<OAuth2GoogleResponse>> oauth2GoogleLogin(
            @RequestParam("code") String code, @RequestParam("scope") String scope) {
        OAuth2GoogleRequest tokenRequest = OAuth2GoogleRequest.builder()
                .clientId("790391901423-0eo9sf6ubogjlkvqfvu0k6puvafbfhlh.apps.googleusercontent.com")
                .clientSecret("SM8iDib0B3uBM4Imu_1o9ySS")
                .code(code)
                .grantType("authorization_code")
                .redirectUri("http://localhost:8080/login/oauth2/google")
                .build();

        ResponseEntity<OAuth2GoogleResponse> httpResponse = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v4/token",
                HttpMethod.POST,
                new HttpEntity<>(tokenRequest),
                new ParameterizedTypeReference<OAuth2GoogleResponse>() {}
        );

        // Get oauth2 response
        OAuth2GoogleResponse oAuth2GoogleResponse = httpResponse.getBody();
        if (oAuth2GoogleResponse == null) {
            return ResponseFactory.fail(null);
        }
        oAuth2GoogleResponse.getUrl().put(
                "Get user information",
                "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                        + oAuth2GoogleResponse.getAccessToken()
        );
        return ResponseFactory.success(oAuth2GoogleResponse);
    }

    @GetMapping("/oauth2/github")
    public ResponseEntity<GeneralResponse<OAuth2GithubResponse>> oauth2GithubLogin(
            @RequestParam("code") String code) {
        OAuth2GithubRequest tokenRequest = OAuth2GithubRequest.builder()
                .clientId("f53e9168370310a56ce5")
                .clientSecret("bd80811dee82f8865f293744cfbf0ba81051407a")
                .code(code)
                .state("abc")
                .redirectUri("http://localhost:8080/login/oauth2/github")
                .build();

        // headers
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        // Rest Template call
        ResponseEntity<OAuth2GithubResponse> httpResponse = restTemplate.exchange(
                "https://github.com/login/oauth/access_token",
                HttpMethod.POST,
                new HttpEntity<>(tokenRequest, headers),
                new ParameterizedTypeReference<OAuth2GithubResponse>() {}
        );

        // Get oauth2 response
        OAuth2GithubResponse oAuth2GithubResponse = httpResponse.getBody();
        if (oAuth2GithubResponse == null) {
            return ResponseFactory.fail(null);
        }
        oAuth2GithubResponse.getUrl().put(
                "User information",
                "curl --location --request GET 'https://api.github.com/user' " +
                        "--header 'Authorization: " + oAuth2GithubResponse.getTokenType() + " " + oAuth2GithubResponse.getAccessToken() + "'"
        );
        return ResponseFactory.success(oAuth2GithubResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<GeneralResponse<OAuth2LoginInfoResponse>> loginInfo() {
        OAuth2LoginInfoResponse responseInfo = OAuth2LoginInfoResponse.builder()
                .oauthGoogle(OAuth2InfoDTO.builder()
                        .oauthUrl("https://accounts.google.com/o/oauth2/v2/auth?client_id=790391901423" +
                                "-0eo9sf6ubogjlkvqfvu0k6puvafbfhlh.apps.googleusercontent.com&scope=https://www" +
                                ".googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/user" +
                                ".phonenumbers.read https://www.googleapis.com/auth/user.organization" +
                                ".read&redirect_uri=http://localhost:8080/login/oauth2/google&response_type=code" +
                                "&prompt=consent&access_type=offline")
                        .scope("https://developers.google.com/identity/protocols/oauth2/scopes")
                        .build())
                .oauthGithub(OAuth2InfoDTO.builder()
                        .oauthUrl("https://github.com/login/oauth/authorize?client_id=f53e9168370310a56ce5&" +
                                "redirect_uri=http://localhost:8080/login/oauth2/github" +
                                "&state=abc&scope=user,repo&allow_signup=true")
                        .scope("https://docs.github.com/en/developers/apps/building-oauth-apps/scopes-for-oauth-apps")
                        .build())
                .build();
        return ResponseFactory.success(responseInfo);
    }

}
