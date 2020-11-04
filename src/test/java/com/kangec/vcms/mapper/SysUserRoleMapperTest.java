package com.kangec.vcms.mapper;

import com.kangec.vcms.VcmsApplication;
import com.kangec.vcms.entity.SysUserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author kangec 11/4/2020 11:34 PM
 * @Email ardien@126.com
 **/
@SpringBootTest(classes = VcmsApplication.class)
public class SysUserRoleMapperTest {

    @Autowired
    public SysUserRoleMapper sysUserRoleMapper;

    @Test
    public void listByUserId() {
        insertUserRole();
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.listByUserId(4);
        Assertions.assertNotNull(sysUserRoles);
        Optional<SysUserRole> first = sysUserRoles
                .stream()
                .filter(sysUserRole -> sysUserRole.getRoleId() == 2)
                .filter(sysUserRole -> sysUserRole.getUserId() == 4)
                .findFirst();
        Assertions.assertNotNull(first.get());
        first.get().setRoleId(3);
        updateUserRoleByUserId(first.get());
        delete(first.get());
    }

    public void insertUserRole() {
        Long aLong = sysUserRoleMapper.insertUserRole(4, 2);
        Assertions.assertEquals(aLong, 1L);
    }

    public void updateUserRoleByUserId(SysUserRole sysUserRole) {
        Long aLong = sysUserRoleMapper.updateUserRoleByUserId(sysUserRole);
        Assertions.assertEquals(aLong, 1L);
    }

    void delete(SysUserRole sysUserRole) {
        Long aLong = sysUserRoleMapper.delete(sysUserRole);
        Assertions.assertEquals(aLong, 1L);
    }
}