package com.kangec.vcms.utils.logging;

import com.kangec.vcms.utils.logging.annotation.LogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kangec
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LogDO implements Serializable {
    private String id;
    /**
     * 用户id
     */
    private String userId;

    /**
     *  用户名
     */
    private String username;
    /**
     * 运行时间
     */
    private String runTime;

    /**
     * 日志主诉
     */
    private LogLevel logFocus;
    /**
     * 类型
     */
    private LogType logType;
    /**
     *  方法
     */
    private String logFunction;

    /**
     * 参数
     */
    private String logArgsFocus;

    /**
     *  简述
     */
    private String logSummary;
    /**
     * 返回值
     */
    private String returnValue;
    /**
     * 时间
     */
    private LocalDateTime createDate;
}
