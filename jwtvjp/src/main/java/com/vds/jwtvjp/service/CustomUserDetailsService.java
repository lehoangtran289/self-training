package com.vds.jwtvjp.service;

import com.vds.jwtvjp.entity.CustomUser;
import com.vds.jwtvjp.entity.CustomUserDetails;
import com.vds.jwtvjp.repository.CustomUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final CustomUserRepository customUserRepository;

    public CustomUserDetailsService(CustomUserRepository customUserRepository) {
        this.customUserRepository = customUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser user = customUserRepository.findByUsername(username);
        if (user == null) {
            log.error("Cannot find user with username {}", username);
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        CustomUser user = customUserRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException(String.valueOf(id))
        );
        return new CustomUserDetails(user);
    }
}
