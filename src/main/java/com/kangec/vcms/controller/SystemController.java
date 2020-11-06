package com.kangec.vcms.controller;

import com.kangec.vcms.controller.vo.VoRole;
import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.ResultResponse;
import com.kangec.vcms.service.SystemService;
import com.kangec.vcms.utils.logging.annotation.Log;
import com.kangec.vcms.utils.logging.annotation.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 系统资源控制器
 *
 * @Author kangec 11/5/2020 7:30 PM
 * @Email ardien@126.com
 **/
@RestController
@RequestMapping("/api/system")
public class SystemController {
    static Map<String, Object> data = new HashMap<>();

    static {
        List<VoRole> roles = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            roles.add(new VoRole(String.valueOf(i),"ROLE_ADMIN_" + i, "管理员_" + i, "启用","添加", LocalDateTime.now()));
        }
        data.put("roles",roles);
    }

    @Autowired
    private SystemService systemService;

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

    @GetMapping("/roles")
    public ResultResponse roleList() {
        return ResultResponse.ok(data);
    }

    @Log(detail = "", type = LogType.DELETE)
    @DeleteMapping("/roles/{roleId}")
    public ResultResponse deleteRole(@PathVariable String roleId) {
        List<VoRole> roles = (List<VoRole>) data.get("roles");
        if ((!roleId.isEmpty()) && roles != null && roles.size() > 0) {
            for (int i = 0; i < roles.size(); i++) {
                VoRole voRole = roles.get(i);
                if (voRole.getRoleId().equals(roleId)) {
                    roles.remove(voRole);
                    System.out.println("移除" + voRole);
                }
            }
        }
        return ResultResponse.ok(null);
    }

    @PatchMapping("/roles")
    public ResultResponse updateRole(@RequestBody VoRole voRole) {
        List<VoRole> roles = (List<VoRole>) data.get("roles");
        return ResultResponse.ok(null);
    }

    @GetMapping("/evn")
    public ResultResponse getSystemInfatuation() {
        Map<String, Object> runtimeEvn = null;
        try {
            runtimeEvn = systemService.getSystemRuntimeEvn();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResultResponse.ok(runtimeEvn);
    }
}
