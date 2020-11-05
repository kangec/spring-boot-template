package com.kangec.vcms.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.utils.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * 拦截登录请求中的账号密码进行校验，校验成功发布Token
 *
 * @Author kangec 11/5/2020 5:14 PM
 * @Email ardien@126.com
 **/
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager manager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(manager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest
                                                , HttpServletResponse httpServletResponse)
                                                        throws AuthenticationException, IOException, ServletException {
        VoUser user = new ObjectMapper().readValue(httpServletRequest.getInputStream(), VoUser.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req
                                            , HttpServletResponse resp
                                            , FilterChain chain
                                            , Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }

        String token = JwtUtil.generateToken(authResult.getName(), as.toString());
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write(new ObjectMapper().writeValueAsString(token));
        out.flush();
        out.close();
    }
    protected void unsuccessfulAuthentication(HttpServletRequest req
                                            , HttpServletResponse resp
                                            , AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.write("登录失败!");
        out.flush();
        out.close();
    }
}