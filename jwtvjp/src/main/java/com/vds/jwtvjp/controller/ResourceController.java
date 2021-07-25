package com.vds.jwtvjp.controller;

import com.vds.jwtvjp.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/api")
public class ResourceController {

    @GetMapping("/resource")
    public ResponseEntity<?> getResource() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Pr0Vjp Resource"));
    }
}
