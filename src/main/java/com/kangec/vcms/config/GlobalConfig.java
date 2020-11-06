package com.kangec.vcms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

/**
 * @Author kangec 10/26/2020 9:19 AM
 * @Email ardien@126.com
 **/

@Configuration
public class GlobalConfig {

    @Bean
    public SystemInfo getSystemInfo() {
        return new SystemInfo();
    }
}
