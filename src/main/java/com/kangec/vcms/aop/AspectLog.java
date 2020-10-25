package com.kangec.vcms.aop;

import com.kangec.vcms.service.SysOperatorLogService;
import com.kangec.vcms.utils.logging.LogDO;
import com.kangec.vcms.utils.logging.LogManager;
import com.kangec.vcms.utils.logging.annotation.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author PC
 */
@Aspect
@Component
public class AspectLog {

    @Autowired
    private LogManager logManager;
    private SysOperatorLogService sysOperatorLogService;
    private final ThreadLocal<LocalDateTime> localDateTime = new ThreadLocal<>();
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();


    /**
     * 声明切入点在注解{@link com.kangec.vcms.utils.logging.annotation.Log}上
     */
    @Pointcut("@annotation(com.kangec.vcms.utils.logging.annotation.Log)")
    public void log() {
    }

    /**
     * 记录方法开始运行的时间
     * 创建时间
     * 统计性能
     */
    @Before("log()")
    public void doBefore() {
        Long start = System.currentTimeMillis();
        startTime.set(start);
        localDateTime.set(LocalDateTime.now());
    }


    @AfterReturning("log()")
    public void doAfterReturning(JoinPoint joinPoint) {
        Long end = System.currentTimeMillis();
        asyncDoLog(joinPoint, end - startTime.get(), localDateTime.get());
        localDateTime.remove();
        startTime.remove();
    }

    private void asyncDoLog(JoinPoint joinPoint,Long runTime, LocalDateTime localDateTime) {
        logManager.submit(() -> {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Log annotation = null;
            Method method = signature.getMethod();
            Class<?> type = method.getReturnType();
            try {
                annotation = method.getAnnotation(Log.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Map<String, Object> fieldsName = getFieldsName(joinPoint);
            LogDO log = LogDO.builder()
                    .createDate(localDateTime)
                    .runTime(String.valueOf(runTime))
                    .logFunction(null)
                    .logFocus(annotation.level())
                    .logType(annotation.type())
                    .logSummary(annotation.detail())
                    .logArgsFocus(fieldsName == null ? "Null" : fieldsName.toString())
                    .returnValue(type.getName())
                    .build();
            sysOperatorLogService.addLogRecord(log);
            System.out.println(log);
        });
    }

    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取属性名称
     *
     * @param joinPoint JoinPoint
     * @return 属性名称
     */
    public Map<String, Object> getFieldsName(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0) {
            return null;
        }
        for (Object arg : args) {
            if (arg instanceof MultipartFile || arg instanceof ServletRequest || arg instanceof ServletResponse) {
                return null;
            }
        }
        ParameterNameDiscoverer pnd = new DefaultParameterNameDiscoverer();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = pnd.getParameterNames(method);
        Map<String, Object> paramMap = new HashMap<>(32);
        for (int i = 0; i < Objects.requireNonNull(parameterNames).length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }

    @Autowired
    public void setSysOperatorLogService(SysOperatorLogService sysOperatorLogService) {
        this.sysOperatorLogService = sysOperatorLogService;
    }
}