package com.vds.oauthx.payload.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OAuth2GoogleResponse {
    private String accessToken;
    private String expiresIn;
    private String scope;
    private String tokenType;
    private String idToken;
    private String refreshToken;

    @JsonIgnore
    private Map<String, String> url = new HashMap<>();
}
