package com.kangec.vcms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体对象
 * @author kangec
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    private Integer id;
    private String name;
    private String password;
}
