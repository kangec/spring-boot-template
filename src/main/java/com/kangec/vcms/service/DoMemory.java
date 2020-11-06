package com.kangec.vcms.service;

import lombok.Data;
import oshi.hardware.GlobalMemory;

/**
 * @Author kangec 11/6/2020 2:53 PM
 * @Email ardien@126.com
 **/

@Data
public class DoMemory {
    private String total;
    private String used;
    private String available;
    private String usage;

    public DoMemory(GlobalMemory memory) {
        double GB =  (1024D*1024D*1024D);
        double total = memory.getTotal() / GB;
        double available = memory.getAvailable() / GB;
        double used = total - available;
        double usage = (available / total) * 100;

        this.total = String.format("%.2f", total);
        this.available = String.format("%.2f", available);
        this.used = String.format("%.2f", used);
        this.usage = String.format("%.2f", usage);
    }
}
