package com.kangec.vcms.utils.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class LogManager {

    private ThreadPoolTaskExecutor executor;
    /**
     * 异步执行日志处理操作
     */
    public void submit(Runnable task) {
        executor.execute(task);
    }

    @Autowired
    public  void setExecutor(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }
}
