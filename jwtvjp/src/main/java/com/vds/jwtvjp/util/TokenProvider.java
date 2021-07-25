package com.vds.jwtvjp.util;

import com.vds.jwtvjp.entity.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class TokenProvider {
    private final String jwtSecret;
    private final Long expTime;

    public TokenProvider(@Value("${jwt-secret}") String jwtSecret, @Value("${exp-time}") Long expTime) {
        this.jwtSecret = jwtSecret;
        this.expTime = expTime;
    }

    public String generateToken(CustomUserDetails customUserDetails) {
        return Jwts.builder()
                .setSubject(String.valueOf(customUserDetails.getUser().getId()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expTime))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
