package com.kangec.vcms.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author kangec 11/5/2020 8:05 PM
 * @Email ardien@126.com
 **/

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoRole {
    private String roleId;
    private String role;
    private String name;
    private String status;
    private String permission;
    private LocalDateTime createTime;
}
