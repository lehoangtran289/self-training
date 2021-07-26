package com.vds.oauthx.config.resttemplate.interceptor;

import com.vds.oauthx.config.resttemplate.UtilsRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class InterceptorDefault implements ClientHttpRequestInterceptor {
    private final UtilsRestTemplate utilsRestTemplate;

    public InterceptorDefault(UtilsRestTemplate utilsRestTemplate) {
        this.utilsRestTemplate = utilsRestTemplate;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request,
                                        byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        try {
            HttpHeaders headers = request.getHeaders();
            utilsRestTemplate.addHeader(headers, "Accept-Language", "vi");
        } catch (Exception e) {
            log.error("Interceptor Rest error : ", e);
        }
        ClientHttpResponse httpResponse = execution.execute(request, body);
        utilsRestTemplate.trace(request, body, httpResponse);
        return httpResponse;
    }
}
