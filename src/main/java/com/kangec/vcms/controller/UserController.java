package com.kangec.vcms.controller;

import com.kangec.vcms.utils.logging.annotation.Log;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get")
    @Log
    public Object get(Authentication authentication) {
        return authentication.getPrincipal();
    }
}
