package com.kangec.vcms.controller;

import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.utils.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/get")
    public Object get(Authentication authentication, HttpServletRequest request) {
        final String header = request.getHeader("Authorization");

        return JwtUtil.validateToken(header);
    }
    @GetMapping("/info")
    public ResultResponse userInfo(String token) {
        Map<String, String> data = new HashMap<>();
        if ("admin-token".equals(token)) {
            data.put("role", "admin");
            data.put("introduction", "I am a super administrator");
            data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
            data.put("name", "Super Admin");
            return ResultResponse.ok(data);
        }
        return ResultResponse.fail(null);
    }
}
