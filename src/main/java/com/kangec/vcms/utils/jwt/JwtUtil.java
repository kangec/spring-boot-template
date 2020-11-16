package com.kangec.vcms.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

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
    public static String generateToken(String username, String roles) {

        String token = Jwts.builder()
                .setSubject(username)
                .claim("authorities", roles)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DATE))
                .signWith(SignatureAlgorithm.HS256, SECRET_KAY.getBytes(StandardCharsets.UTF_8))
                .compact();

        return "Bearer " + token;
    }

    /**
     * Token校验
     *
     * @param token the token
     */
    public static Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KAY.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();
        } catch (Exception e) {
            throw new IllegalStateException("Invalid Token.");
        }
    }
}
