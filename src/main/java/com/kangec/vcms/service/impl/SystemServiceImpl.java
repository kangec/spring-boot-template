package com.kangec.vcms.service.impl;

import com.kangec.vcms.service.DoCPU;
import com.kangec.vcms.service.DoDiskStores;
import com.kangec.vcms.service.DoMemory;
import com.kangec.vcms.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author kangec 11/6/2020 2:21 PM
 * @Email ardien@126.com
 **/
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private SystemInfo systemInfo;

    @Override
    public Map<String, Object> getSystemRuntimeEvn() {
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        CentralProcessor cpu = hardware.getProcessor();
        GlobalMemory memory = hardware.getMemory();
        List<HWDiskStore> diskStores = hardware.getDiskStores();
        Map<String, Object> evn = new HashMap<>();
        evn.put("cpu",new DoCPU(cpu));
        evn.put("mem", new DoMemory(memory));
        evn.put("diskStores", new DoDiskStores(diskStores));
        return evn;
    }
}
