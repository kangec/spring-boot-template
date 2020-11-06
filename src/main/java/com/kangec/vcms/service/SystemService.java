package com.kangec.vcms.service;

import java.util.Map;

/**
 * @Author kangec 11/6/2020 2:20 PM
 * @Email ardien@126.com
 **/

public interface SystemService {
    /**
     * 获取应用运行环境的参数
     * @return 参数
     */
    public Map<String, Object> getSystemRuntimeEvn();
}
