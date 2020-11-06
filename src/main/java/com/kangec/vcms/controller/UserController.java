package com.kangec.vcms.controller;

import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.utils.jwt.JwtUtil;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    static Map<String, Object> data = new HashMap<>();

    static {
        List<VoUser> users = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            users.add(new VoUser("username", "password", "老能", "人力资源", "14444444", "ADMIN", "启用", LocalDateTime.now()));
        }
        data.put("users", users);
    }

    @GetMapping("/user/get")
    public Object get(Authentication authentication, HttpServletRequest request) {
        final String header = request.getHeader("Authorization");

        return JwtUtil.validateToken(header);
    }

    @GetMapping("/user/info")
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

    @GetMapping("/user")
    public ResultResponse userList() {
        return ResultResponse.ok(data);
    }
}
