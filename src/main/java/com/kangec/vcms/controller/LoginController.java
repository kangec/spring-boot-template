package com.kangec.vcms.controller;

import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.utils.logging.annotation.Log;
import com.kangec.vcms.utils.logging.annotation.LogType;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author kangec 10/16/2020 1:45 PM
 * @Email ardien@126.com
 **/

@RestController
public class LoginController {

    @GetMapping("/hello")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN','ROLE_DRIVER')")
    @Log(detail = "测试", level = LogLevel.INFO, type = LogType.SELECT)
    public void hello(HttpSession session) {

    }

    @PostMapping("/admin/login")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResultResponse login(@RequestBody VoUser user) {
        return null;
    }
}
