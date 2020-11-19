package com.kangec.vcms.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.entity.SysUser;
import com.kangec.vcms.utils.Constants;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 登录过滤器
 *
 * @author Kangec
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {


    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException, IOException {
        SysUser user = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse resp, FilterChain chain, Authentication authResult) throws IOException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for (GrantedAuthority authority : authorities) {
            as.append(authority.getAuthority())
                    .append(",");
        }
        String token = JwtUtil.generateToken(authResult.getName(), as.toString());

        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        ResultResponse response = ResultResponse.login(Constants.STATUS_OK,"登录成功！",data);
        out.write(new ObjectMapper().writeValueAsString(response));
        out.flush();
        out.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        ResultResponse failResp = ResultResponse.login(Constants.STATUS_FAIL, "登录失败，请检查用户名和密码！", null);
        out.write(new ObjectMapper().writeValueAsString(failResp));
        out.flush();
        out.close();
    }
}