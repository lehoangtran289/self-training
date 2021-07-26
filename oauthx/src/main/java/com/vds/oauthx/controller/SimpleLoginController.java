package com.vds.oauthx.controller;

import com.vds.oauthx.payload.general.GeneralResponse;
import com.vds.oauthx.payload.general.ResponseFactory;
import com.vds.oauthx.payload.request.OAuth2GoogleRequest;
import com.vds.oauthx.payload.response.OAuth2GoogleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<GeneralResponse<OAuth2GoogleResponse>> oauth2Login(
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
        if (oAuth2GoogleResponse == null || oAuth2GoogleResponse.getUrl() == null) {
            return ResponseFactory.fail(oAuth2GoogleResponse);
        }
        oAuth2GoogleResponse.getUrl().put(
                "Lấy thông tin user",
                "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
                        + oAuth2GoogleResponse.getAccessToken()
        );
        return ResponseFactory.success(oAuth2GoogleResponse);
    }

    @GetMapping("/info")
    public ResponseEntity<GeneralResponse<Map<String, String>>> loginInfo() {
        Map<String, String> urlInfo = new HashMap<>();
        urlInfo.put("OAuth URL",
                "https://accounts.google.com/o/oauth2/v2/auth?client_id=790391901423" +
                        "-0eo9sf6ubogjlkvqfvu0k6puvafbfhlh.apps.googleusercontent.com&scope=https://www" +
                        ".googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/user" +
                        ".phonenumbers.read https://www.googleapis.com/auth/user.organization" +
                        ".read&redirect_uri=http://localhost:8080/login/oauth2/google&response_type=code" +
                        "&prompt=consent&access_type=offline"
        );
        urlInfo.put("Google scopes",
                "https://developers.google.com/identity/protocols/oauth2/scopes"
        );
        return ResponseFactory.success(urlInfo);
    }

}
