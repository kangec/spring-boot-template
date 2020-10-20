package com.kangec.vcms.service.impl;

import com.kangec.vcms.mapper.SysOperatorLogMapper;
import com.kangec.vcms.service.SysOperatorLogService;
import com.kangec.vcms.utils.logging.LogDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kangec
 */
@Service
public class SysOperatorLogServiceImpl implements SysOperatorLogService {


    private SysOperatorLogMapper sysOperatorLogMapper;

    @Override
    public void addLogRecord(LogDO log) {
        sysOperatorLogMapper.insert(log);
    }

    @Autowired
    public void setSysOperatorLogMapper(SysOperatorLogMapper sysOperatorLogMapper) {
        this.sysOperatorLogMapper = sysOperatorLogMapper;
    }
}
