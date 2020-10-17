package com.kangec.vcms.service;

import com.kangec.vcms.entity.SysRole;
import com.kangec.vcms.entity.SysUser;
import com.kangec.vcms.entity.SysUserRole;

import java.util.List;

/**
 * @Author Ardien
 * @Date 10/15/2020 4:47 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

public interface AuthorityService {
    /**
     * 获取用户信息
     * @param username
     * @return
     */
    public SysUser getSysUserInformation(String username);

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    public SysRole getSysRole(Integer id);

    /**
     * 获取用户的角色信息
     * @param userId
     * @return
     */
    public List<SysUserRole> getSysUserRoleList(Integer userId);
}
