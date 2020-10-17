package com.kangec.vcms.utils.logging;

import com.kangec.vcms.utils.logging.annotation.LogType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.logging.LogLevel;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class LogDO implements Serializable {
    private String id;
    private String userId;      //用户id
    private String username;    // 用户名
    private String runTime;     // 运行时间
    private LogLevel logFocus;    // 日志主诉
    private LogType logType;    // 类型
    private String logFunction; // 方法
    private String logArgsFocus;     // 参数
    private String logSummary;  //  简述
    private String returnValue; //  返回值
    private LocalDateTime createDate; //   时间
}
