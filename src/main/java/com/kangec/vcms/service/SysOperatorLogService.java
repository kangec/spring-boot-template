package com.kangec.vcms.service;

import com.kangec.vcms.utils.logging.LogDO;

/**
 * 系统日志操作接口
 *
 * @author kangec
 */
public interface SysOperatorLogService {

    /**
     * 添加日志
     * @param log LogBean
     */
    void addLogRecord(LogDO log);

}
