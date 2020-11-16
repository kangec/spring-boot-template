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
    @Autowired
    private SystemService systemService;

    static Map<String, Object> data = new HashMap<>();

    static {
        List<VoRole> roles = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            roles.add(new VoRole(String.valueOf(i),"ROLE_ADMIN_" + i, "管理员_" + i, "启用","添加", LocalDateTime.now()));
        }
        data.put("roles",roles);
    }

    /**
     * 登录请求处理接口
     * @param user User DTO，只包含用户名和密码。
     * @return 结果
     */
    @PostMapping("/login")
    public ResultResponse login(@RequestBody VoUser user) {
        Map<String, String> data = new HashMap<>();
        if ("admin".equals(user.getUsername())) {
            data.put("token", "admin-token");
            return ResultResponse.ok(data);
        }
        return ResultResponse.fail(null);
    }

    /**
     * 登出处理接口
     * @return 结果
     */
    @PostMapping("/logout")
    public ResultResponse logout() {
        return ResultResponse.ok(null);
    }

    /**
     * 请求查询角色列表处理接口
     * @return 角色列表
     */
    @GetMapping("/roles")
    public ResultResponse roleList() {
        return ResultResponse.ok(data);
    }

    /**
     * 请求删除角色接口
     * @param roleId 角色Id
     * @return 结果
     */
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

    /**
     * 请求更新角色信息接口
     * @param voRole Role DTO
     * @return 结果
     */
    @PatchMapping("/roles")
    public ResultResponse updateRole(@RequestBody VoRole voRole) {
        List<VoRole> roles = (List<VoRole>) data.get("roles");
        return ResultResponse.ok(null);
    }

    /**
     * 请求查询系统信信息、运行环境、JVM信息、内存信息等
     * @return 信息集合
     */
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
