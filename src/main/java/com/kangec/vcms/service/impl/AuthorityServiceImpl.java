package com.kangec.vcms.service.impl;

import com.kangec.vcms.entity.SysRole;
import com.kangec.vcms.entity.SysUser;
import com.kangec.vcms.entity.SysUserRole;
import com.kangec.vcms.mapper.SysRoleMapper;
import com.kangec.vcms.mapper.SysUserMapper;
import com.kangec.vcms.mapper.SysUserRoleMapper;
import com.kangec.vcms.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Ardien
 * @Date 10/15/2020 4:49 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private SysRoleMapper sysRoleMapper;
    private SysUserMapper sysUserMapper;
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public SysUser getSysUserInformation(String username) {
        return sysUserMapper.queryUserByUsername(username);
    }

    @Override
    public SysRole getSysRole(Integer id) {
        return sysRoleMapper.queryById(id);
    }

    @Override
    public List<SysUserRole> getSysUserRoleList(Integer userId) {
        return sysUserRoleMapper.listByUserId(userId);
    }


    @Autowired
    public void setSysRoleMapper(SysRoleMapper sysRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
    }
    @Autowired
    public void setSysUserMapper(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }
    @Autowired
    public void setSysUserRoleMapper(SysUserRoleMapper sysUserRoleMapper) {
        this.sysUserRoleMapper = sysUserRoleMapper;
    }
}
