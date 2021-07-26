package com.vds.oauthx.config.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class UtilsRestTemplate {

    public void trace(HttpRequest request, byte[] body, ClientHttpResponse response) {
        StringBuilder str = new StringBuilder();

        try (InputStream inputStreamBody = response.getBody();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStreamBody, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            str.append("\n Request = ").append(" \n")
                    .append("Request to : ").append(request.getURI()).append(" \n")
                    .append("Method     : ").append(request.getMethodValue()).append(" \n")
                    .append("Header     : ").append(request.getHeaders()).append(" \n")
                    .append("Body       : ").append(new String(body, StandardCharsets.UTF_8)).append(" \n")
                    .append(" \n");
            str.append("Response = ").append(" \n")
                    .append("Status code  : {}").append(response.getStatusCode()).append(" \n")
                    .append("Headers      : {}").append(response.getHeaders()).append(" \n");

            //Response
            StringBuilder inputStringBuilder = new StringBuilder();

            String line = bufferedReader.readLine();
            while (line != null) {
                inputStringBuilder.append(line);
                inputStringBuilder.append('\n');
                line = bufferedReader.readLine();
            }
            str.append("Response body: {}").append(inputStringBuilder).append(" \n").append(" \n");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        } finally {
            log.info(str.toString());
        }
    }

    public void addHeader(HttpHeaders header, String headerName, String headerValue) {
        if (!header.containsKey(headerName)) {
            header.add(headerName, headerValue);
        }
    }
}
