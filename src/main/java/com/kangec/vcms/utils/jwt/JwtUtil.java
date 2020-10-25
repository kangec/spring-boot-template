package com.kangec.vcms.utils.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Token 生成和校验工具
 *
 * @author kangec
 */
public class JwtUtil {
    public static final String SECRET_KAY = "KANGEC_ENCODE_DECODE";
    public static final Long EXPIRE_DATE = 3600_000_000L;

    /**
     * 生成Token
     *
     * @param username 当前登录用户
     * @return token
     */
    public static String generateToken(String username, String role) {
        Map<String, Object> userData = new HashMap<>(8);
        userData.put("username", username);

        String token = Jwts.builder().setClaims(userData)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DATE))
                .signWith(SignatureAlgorithm.HS256, SECRET_KAY)
                .compact();

        return "Bearer" + token;
    }

    /**
     * Token校验
     *
     * @param token the token
     */
    public static Object validateToken(String token) {
        try {
            Map<String, Object> body = Jwts.parser()
                    .setSigningKey(SECRET_KAY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token.replace("bearer ", ""))
                    .getBody();
            return body;
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Token.");
        }
    }
}
