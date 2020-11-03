package com.kangec.vcms.mapper;

import com.kangec.vcms.VcmsApplication;
import com.kangec.vcms.entity.SysRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试角色表CRUD
 */
@SpringBootTest(classes = VcmsApplication.class)
class SysRoleMapperTest {

    @Autowired
    public SysRoleMapper sysRoleMapper;

    @Test
    public void queryById() {
        final SysRole role = insertRole();
        Assertions.assertNotNull(role);
        final SysRole sysRole = sysRoleMapper.queryById(role.getId());
        Assertions.assertEquals(sysRole.getName(), "ROLE_TEST");
        sysRole.setName("ROLE_TEST_2");
        updateRole(sysRole);

        final SysRole sysRoleUpdate = sysRoleMapper.queryById(sysRole.getId());
        Assertions.assertEquals(sysRoleUpdate.getName(), "ROLE_TEST_2");
        deleteRole(sysRole);
    }

    public SysRole insertRole() {
        final SysRole role_test = new SysRole(null, "ROLE_TEST");
        sysRoleMapper.insertRole(role_test);
        return role_test;
    }


    public void updateRole(SysRole sysRole) {
        sysRoleMapper.updateRole(sysRole);
    }


    public void deleteRole(SysRole sysRole) {
        final Long role = sysRoleMapper.deleteRole(sysRole.getId());
        Assertions.assertEquals(role,1);
    }
}