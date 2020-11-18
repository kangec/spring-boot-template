package com.kangec.vcms.controller;

import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangec
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{userId}")
    public Object getUser(@PathVariable(value = "userId" ) String userId) {
        VoUser voUser = userService.getUserByUserId(userId);
        return ResultResponse.ok(voUser);
    }

    @GetMapping("/user/info")
    public ResultResponse userInfo() {
        Map<String, String> data = new HashMap<>();
        data.put("role", "admin");
        data.put("introduction", "I am a super administrator");
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("name", "Super Admin");
        return ResultResponse.ok(data);
    }

    @GetMapping("/user")
    public ResultResponse userList() {
        List<VoUser> data = userService.getUserList();
        return ResultResponse.ok(data);
    }
}
