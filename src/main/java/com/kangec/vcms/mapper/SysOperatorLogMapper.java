package com.kangec.vcms.mapper;

import com.kangec.vcms.utils.logging.LogDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SysOperatorLogMapper {
    void insert(LogDO log);
}
