package com.kangec.vcms.service;

import lombok.Data;
import oshi.hardware.CentralProcessor;

/**
 * @Author kangec 11/6/2020 2:46 PM
 * @Email ardien@126.com
 **/

@Data
public class DoCPU {
    private Integer physicalProcessorCount;
    private Integer logicalProcessorCount;
    private String name;
    private Double free;

    public DoCPU(CentralProcessor centralProcessor) {
        this.logicalProcessorCount = centralProcessor.getLogicalProcessorCount();
        this.physicalProcessorCount = centralProcessor.getPhysicalProcessorCount();
        this.name = centralProcessor.getProcessorIdentifier().getName();
        this.free = 20.00D;
    }
}
