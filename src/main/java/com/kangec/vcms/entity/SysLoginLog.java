package com.kangec.vcms.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author kangec 11/17/2020 8:31 PM
 * @Email ardien@126.com
 **/

@Data
@Builder
public class SysLoginLog {
    private Integer id;
    private String ip;
    private String address;
    private String browser;
    private String system;
    private String loginStatus;
    private String loginInfo;
    private LocalDateTime loginTime;
}
