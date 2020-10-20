package com.kangec.vcms.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangec.vcms.entity.SysUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author kangec
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    protected JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {
        SysUser sysUser = new ObjectMapper().readValue(request.getInputStream(), SysUser.class);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(sysUser.getName(), sysUser.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response
                                    , FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response
                                                , AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
