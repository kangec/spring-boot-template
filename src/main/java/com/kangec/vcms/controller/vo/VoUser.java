package com.kangec.vcms.controller.vo;

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
    private String userId;
    private String username;
    private String password;
    private String nickName;
    private String department;
    private String phone;
    private String role;
    private String status;
    private LocalDateTime createTime;
}
