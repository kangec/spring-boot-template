package com.kangec.vcms.utils.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kangec
 */
@Component
public class LogManager {

    private ThreadPoolTaskExecutor executor;
    /**
     * 异步执行日志处理操作
     */
    public void submit(Runnable task) {
        executor.execute(task);
    }

    /**
     * 比较两个对象内的属性是否改变，记录他们前后变化的值。
     *
     * @param oldObj 旧对象
     * @param newObj 新对象
     * @return 发生变化的字段
     */
    public static Map<String, String> diffObj(Object oldObj, Object newObj) {
        HashMap<String, String> diffData = new HashMap<>();
        try {
            Class<?> oldClazz = oldObj.getClass();
            Class<?> newClazz = newObj.getClass();
            if (oldClazz.equals(newClazz)) {
                Field[] oldClazzDeclaredFields = oldClazz.getDeclaredFields();
                for (Field field : oldClazzDeclaredFields) {
                    field.setAccessible(true);
                    Object oldVal = field.get(oldObj);
                    Object newVal = field.get(newObj);
                    if ((oldVal == null && newVal != null) || (oldVal != null && oldVal.equals(newVal))) {
                        diffData.put(field.getName(), "from " + oldVal + " to " + newVal);
                    }
                }
            }
        } catch (IllegalAccessException e) {
        e.printStackTrace();
    }
        return diffData;
    }

    @Autowired
    public  void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }
}
