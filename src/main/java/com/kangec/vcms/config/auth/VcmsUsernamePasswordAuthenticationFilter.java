package com.kangec.vcms.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @author kangec
 */
public class VcmsUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper mapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authToken = null;
            Map<String, String> authBean = null;
            try (InputStream is = request.getInputStream()) {
                authBean = mapper.readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (!authBean.isEmpty()) {
                    final String username = authBean.get(SPRING_SECURITY_FORM_USERNAME_KEY);
                    final String password = authBean.get(SPRING_SECURITY_FORM_PASSWORD_KEY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
