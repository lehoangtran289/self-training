package com.vds.oauthx.config.resttemplate;

import com.vds.oauthx.config.resttemplate.interceptor.InterceptorDefault;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Configuration
public class RestTemplateDefault {
    private final InterceptorDefault interceptorDefault;

    public RestTemplateDefault(InterceptorDefault interceptorDefault) {
        this.interceptorDefault = interceptorDefault;
    }

    @Bean
    public RestTemplate restTemplateBean() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(interceptorDefault));
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }
}
