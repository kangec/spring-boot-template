package com.kangec.vcms.controller;

import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.ResultResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author kangec 11/5/2020 7:30 PM
 * @Email ardien@126.com
 **/
@RestController
@RequestMapping("/api/system")
public class SystemController {

    @PostMapping("/login")
    public ResultResponse login(@RequestBody VoUser user) {
        Map<String, String> data = new HashMap<>();
        if ("admin".equals(user.getUsername())) {
            data.put("token", "admin-token");
            return ResultResponse.ok(data);
        }
        return ResultResponse.fail(null);
    }
    @PostMapping("/logout")
    public void logout() {

    }
}
