package com.kangec.vcms.mapper;

import com.kangec.vcms.utils.logging.LogDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author kangec
 */
@Component
@Mapper
public interface SysOperatorLogMapper {
    /**
     * 向数据库中插入一条日志记录
     * @param log Log Bean
     */
    void insert(LogDO log);
}
