package com.kangec.vcms.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author kangec 10/25/2020 10:54 PM
 * @Email ardien@126.com
 **/

@Data
public class VoUser implements Serializable {
    private String username;
    private String password;
}
