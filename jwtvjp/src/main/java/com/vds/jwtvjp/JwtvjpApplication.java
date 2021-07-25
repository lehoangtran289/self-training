package com.vds.jwtvjp;

import com.vds.jwtvjp.entity.CustomUser;
import com.vds.jwtvjp.repository.CustomUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
@Slf4j
public class JwtvjpApplication {
    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtvjpApplication(CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder) {
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {
        CustomUser user = new CustomUser();
        user.setUsername("viettel");
        user.setPassword(passwordEncoder.encode("123"));
        customUserRepository.save(user);
        log.info("Save 1 user: {}", user);
    }

    public static void main(String[] args) {
        SpringApplication.run(JwtvjpApplication.class, args);
    }

}
