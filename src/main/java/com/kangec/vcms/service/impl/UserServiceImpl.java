package com.kangec.vcms.service.impl;

import com.kangec.vcms.controller.vo.VoUser;
import com.kangec.vcms.entity.SysUser;
import com.kangec.vcms.mapper.SysUserMapper;
import com.kangec.vcms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author kangec 11/8/2020 8:25 PM
 * @Email ardien@126.com
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<VoUser> getUserList() {
        List<SysUser> users = sysUserMapper.queryUserList();
        List<VoUser> voUserList = new LinkedList<>();
        for (SysUser user : users) {
            VoUser voUser = VoUser.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .userId(user.getId())
                    .createTime(user.getCreateTime())
                    .nickName(user.getNickname())
                    .phone(user.getPhone())
                    .status(user.getStatus())
                    .build();
            voUserList.add(voUser);
        }
        return voUserList;
    }

    @Override
    public VoUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public VoUser getUserByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return null;
        }
        SysUser sysUser = sysUserMapper.queryUserById(userId);
        return sysUser == null ? null : new VoUser(sysUser);
    }

    @Override
    public List<VoUser> getUserByNickname(String nickname) {
        return null;
    }

    @Override
    public List<VoUser> getUserByPhoneNumber(String phone) {
        return null;
    }

    @Override
    public VoUser getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean deleteUserByUserId(String userId) {
        return false;
    }

    @Override
    public boolean addUser(VoUser voUser) {
        return false;
    }

    @Override
    public boolean updateUser(VoUser newUser) {
        return false;
    }
}
