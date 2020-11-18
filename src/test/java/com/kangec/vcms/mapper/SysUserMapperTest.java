package com.kangec.vcms.mapper;

import com.kangec.vcms.VcmsApplication;
import com.kangec.vcms.entity.SysUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = VcmsApplication.class)
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void sysUserMapperTest() {
        final SysUser sysUser = insertUser();
        queryUser(sysUser);
        updateUser(sysUser);
        deleteUser(sysUser);
    }

    public void queryUser(SysUser sysUser) {
        final SysUser user = sysUserMapper.queryUserByUsername(sysUser.getUsername());
        final SysUser userById = sysUserMapper.queryUserById(String.valueOf(sysUser.getId()));
        Assertions.assertEquals(user, userById);
    }

    public SysUser insertUser() {
        final SysUser sysUser = new SysUser(null, "test", "test",null,null,null,null,null,null);
        sysUserMapper.insertUser(sysUser);
        Assertions.assertNotNull(sysUser);
        return sysUser;
    }

    public void updateUser(SysUser sysUser) {
        sysUser.setPassword("test_test");
        final Integer user = sysUserMapper.updateUser(sysUser);
        Assertions.assertEquals(user, 1);
    }

    public void deleteUser(SysUser sysUser) {
        final Integer integer = sysUserMapper.deleteUser(String.valueOf(sysUser.getId()));
        Assertions.assertEquals(integer, 1);
    }
}