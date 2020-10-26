package com.kangec.vcms.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangec.vcms.controller.vo.VoUser;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义登录过滤器
 *
 * @author kangec
 */
public class VcmsUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE)
                || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            ObjectMapper objectMapper = new ObjectMapper();
            UsernamePasswordAuthenticationToken authToken = null;
            VoUser user = null;
            try (InputStream is = request.getInputStream()) {
                user = objectMapper.readValue(is, VoUser.class);
                authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            } catch (IOException e) {
                e.printStackTrace();
                authToken = new UsernamePasswordAuthenticationToken("", "");
            }finally {
                setDetails(request, authToken);
                return this.getAuthenticationManager().authenticate(authToken);
            }
        }else {
            return super.attemptAuthentication(request, response);
        }
    }
}
