package com.kangec.vcms.service.impl;

import com.kangec.vcms.service.SystemService;
import com.kangec.vcms.service.vo.DoCPU;
import com.kangec.vcms.service.vo.DoDiskStores;
import com.kangec.vcms.service.vo.DoJVM;
import com.kangec.vcms.service.vo.DoMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author kangec 11/6/2020 2:21 PM
 * @Email ardien@126.com
 **/
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemInfo systemInfo;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Override
    public Map<String, Object> getSystemRuntimeEvn() throws ExecutionException, InterruptedException {
        Future<Map<String, Object>> future = threadPoolTaskExecutor.submit(() -> {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
            OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
            HardwareAbstractionLayer hardware = systemInfo.getHardware();
            CentralProcessor cpu = hardware.getProcessor();
            GlobalMemory memory = hardware.getMemory();
            List<HWDiskStore> diskStores = hardware.getDiskStores();
            Map<String, Object> evn = new HashMap<>();
            evn.put("cpu", new DoCPU(cpu, 20));
            evn.put("mem", new DoMemory(memory));
            evn.put("diskStores", DoDiskStores.get(diskStores));
            evn.put("jvm", new DoJVM(memoryMXBean, runtimeMXBean));
            return evn;
        });
        return future.get();
    }
}
