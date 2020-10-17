package com.kangec.vcms.utils.logging.annotation;

import org.springframework.boot.logging.LogLevel;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 日志等级
     */
    LogLevel level() default LogLevel.INFO;

    /**
     * 日志详细信息
     */
    String detail() default "";

    LogType type() default LogType.UNKNOWN;
}
