package com.vds.oauthx.payload.general;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@Slf4j
@UtilityClass
public class ResponseFactory {

    public <T> GeneralResponse<T> getResponse(T t) {
        GeneralResponse<T> generalResponse = new GeneralResponse<>();
        if (t == null) {
            generalResponse.setStatus(400);
        } else {
            generalResponse.setStatus(200);
            generalResponse.setData(t);
        }
        return generalResponse;
    }

    public <T> ResponseEntity<GeneralResponse<T>> success(T data) {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setData(data);
        responseObject.setStatus(200);
        return ResponseEntity.ok().body(responseObject);
    }

    public <T> ResponseEntity<GeneralResponse<T>> fail(T data) {
        GeneralResponse<T> responseObject = new GeneralResponse<>();
        responseObject.setData(data);
        responseObject.setStatus(400);
        return ResponseEntity.status(400).body(responseObject);
    }

}
