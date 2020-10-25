package com.kangec.vcms.config.auth;

import com.kangec.vcms.utils.jwt.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author kangec
 */

@Configuration
public class VcmsTokenStore {

    @Primary
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    @Primary
    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JwtUtil.SECRET_KAY);
        return converter;
    }

    @Primary
    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer() {
        return new JwtTokenEnhancer();
    }
}
