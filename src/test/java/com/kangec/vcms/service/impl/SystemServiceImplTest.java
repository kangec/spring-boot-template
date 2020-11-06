package com.kangec.vcms.service.impl;

import com.kangec.vcms.VcmsApplication;
import com.kangec.vcms.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author kangec 11/6/2020 3:18 PM
 * @Email ardien@126.com
 **/

@SpringBootTest(classes = VcmsApplication.class)
public class SystemServiceImplTest {

    @Autowired
    private SystemService systemService;
    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
    private final RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
    @Test
    public void getSystemRuntimeEvn() throws ExecutionException, InterruptedException {
        Map<String, Object> evn = systemService.getSystemRuntimeEvn();
        System.out.println(memoryMXBean.getHeapMemoryUsage().toString());
        System.out.println(runtimeMXBean.getName());
        System.out.println(runtimeMXBean.getVmName());
        System.out.println(runtimeMXBean.getVmVersion());
        System.out.println(runtimeMXBean.getVmVersion());
        System.out.println(runtimeMXBean.getStartTime());
    }
}