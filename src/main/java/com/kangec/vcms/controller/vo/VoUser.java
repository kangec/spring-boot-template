package com.kangec.vcms.controller.vo;

import com.kangec.vcms.entity.SysUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author kangec 10/25/2020 10:54 PM
 * @Email ardien@126.com
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoUser implements Serializable {
    private Integer userId;
    private String username;
    private String avatar;
    private String password;
    private String nickName;
    private String department;
    private String phone;
    private String role;
    private String status;
    private LocalDateTime createTime;

    public VoUser(SysUser user) {
        this.createTime = user.getCreateTime();
        this.nickName = user.getNickname();
        this.userId = user.getId();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.username = user.getUsername();
        this.status = user.getStatus();
        this.avatar = user.getAvatar();
        this.department = "默认";
        this.role = "默认";
    }
}
