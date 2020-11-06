package com.kangec.vcms.service.impl;

import com.kangec.vcms.VcmsApplication;
import com.kangec.vcms.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author kangec 11/6/2020 3:18 PM
 * @Email ardien@126.com
 **/

@SpringBootTest(classes = VcmsApplication.class)
public class SystemServiceImplTest {

    @Autowired
    private SystemService systemService;

    @Test
    public void getSystemRuntimeEvn() {
        Map<String, Object> evn = systemService.getSystemRuntimeEvn();
        System.out.println(evn.toString());
    }
}