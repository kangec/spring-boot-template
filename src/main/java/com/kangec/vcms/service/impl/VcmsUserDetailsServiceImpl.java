package com.kangec.vcms.service.impl;

import com.kangec.vcms.entity.SysRole;
import com.kangec.vcms.entity.SysUser;
import com.kangec.vcms.entity.SysUserRole;
import com.kangec.vcms.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ardien
 * @Date 10/15/2020 4:36 PM
 **/

@Service("vcmsUserDetailsServiceImpl")
@Slf4j
public class VcmsUserDetailsServiceImpl implements UserDetailsService {
    private AuthorityService authorityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 权限列表
        List<GrantedAuthority> authList = new ArrayList<>();

        // 获取用户信息
        SysUser sysUser = authorityService.getSysUserInformation(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        //获取用户的角色信息，并构建角色列表
        List<SysUserRole> userRoleList = authorityService.getSysUserRoleList(sysUser.getId());
        userRoleList.forEach(sysUserRole -> {
            SysRole sysRole = authorityService.getSysRole(sysUserRole.getRoleId());
            authList.add(new SimpleGrantedAuthority(sysRole.getName()));
        });

        return new User(sysUser.getName(), sysUser.getPassword(), authList);
    }


    @Autowired
    public void setAuthorityService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
