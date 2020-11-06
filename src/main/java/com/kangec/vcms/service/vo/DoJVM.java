package com.kangec.vcms.service.vo;

import lombok.Data;

import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author kangec 11/6/2020 4:31 PM
 * @Email ardien@126.com
 **/

@Data
public class DoJVM {
    private String name;
    private String version;
    private String startTime;
    private String runTime;
    private String home;
    private String appDir;
    private String total;
    private String used;
    private String free;
    private String usage;


    public DoJVM(MemoryMXBean memoryMXBean, RuntimeMXBean runtimeMXBean) {
        double MB = 1024D * 1024D;
        try{
            long diff = System.currentTimeMillis() - runtimeMXBean.getStartTime();//这样得到的差值是毫秒级别
            long days = diff / (1000 * 60 * 60 * 24);
            long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            runTime = ""+days+"天"+hours+"小时"+minutes+"分";
        }catch (Exception e) {

        }
        this.name = runtimeMXBean.getVmName();
        this.version = runtimeMXBean.getVmVersion();
        this.home = System.getProperty("java.home");
        this.appDir = this.getClass().getResource("/").getPath();
        this.startTime = timestampsToDatetime(runtimeMXBean.getStartTime()).toString();
        double total = memoryMXBean.getHeapMemoryUsage().getMax() / MB;
        double used = memoryMXBean.getHeapMemoryUsage().getUsed() / MB;
        double free = total - used;
        double usage = (used / total) * 100;
        this.total = String.format("%.2f", total);
        this.used = String.format("%.2f", used);
        this.free = String.format("%.2f", free);
        this.usage = String.format("%.2f", usage);
    }

    private LocalDateTime timestampsToDatetime(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }
}
