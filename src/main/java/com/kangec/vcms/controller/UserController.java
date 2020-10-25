package com.kangec.vcms.controller;

import com.kangec.vcms.utils.jwt.JwtUtil;
import com.kangec.vcms.utils.logging.annotation.Log;
import com.sun.net.httpserver.HttpServer;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    @Log
    public Object get(Authentication authentication, HttpServletRequest request) {
        final String header = request.getHeader("Authorization");

        return JwtUtil.validateToken(header);
    }
}
