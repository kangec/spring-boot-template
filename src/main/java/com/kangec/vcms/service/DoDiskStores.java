package com.kangec.vcms.service;

import lombok.Data;
import oshi.hardware.HWDiskStore;

import java.util.List;

/**
 * @Author kangec 11/6/2020 3:02 PM
 * @Email ardien@126.com
 **/

@Data
public class DoDiskStores {
    private String dirName;
    private String sysTypeName;
    private String typeName;
    private String total;
    private String used;
    private String free;
    private String usage;

    public DoDiskStores(List<HWDiskStore> diskStores) {
        HWDiskStore store = diskStores.get(0);
        if (store != null) {
            try {
                this.dirName = store.getPartitions().get(0).getMountPoint();
                this.sysTypeName = store.getPartitions().get(0).getType();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                double GB =  (1024D*1024D*1024D);
                double total = store.getSize() / GB;
                double used = store.getSize() / GB;
                double free = total - used;
                double usage = used / total;
                this.typeName = store.getModel();
                this.total = String.format("%.2f", total);
                this.used = String.format("%.2f", used);
                this.free = String.format("%.2f", free);
                this.usage = String.format("%.2f", usage);
            }
        }
    }
}
