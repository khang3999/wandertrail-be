package com.example.wandertrail_gateway_service.util;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
@RefreshScope
public class JwtUtil {
    private final PublicKey publicKey;
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    public JwtUtil(@Value("${jwt.public-key}") String keyFromConsul) throws Exception {
        this.publicKey = loadPublicKey(keyFromConsul);
    }

    /**
     * Chuyển string public key thành PublicKey object
     */
    private PublicKey loadPublicKey(String key) throws Exception {
        key = key.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    /**
     * Validate JWT token và trả về Claims là data(payload) bên trong token
     */
    public Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // =========================
    // Getter cho header & prefix
    // =========================
    public static String getHeader() {
        return HEADER;
    }

    public static String getPrefix() {
        return PREFIX;
    }

}
