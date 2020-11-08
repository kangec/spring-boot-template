package com.kangec.vcms.service;

import com.kangec.vcms.controller.vo.VoUser;

import java.util.List;

/**
 * 用户信息服务公共接口
 *
 * @author kangec
 */
public interface UserService {
    /**
     * 查询所有用户信息接口
     *
     * @return {@link VoUser} 用户信息列表
     */
    List<VoUser> getUserList();

    /**
     * 以用户名获取用户信息
     *
     * @param username 用户名
     * @return {@link VoUser} 用户信息
     */
    VoUser getUserByUsername(String username);

    /**
     * 以用户标识获取用户信息
     *
     * @param userId 用户名
     * @return {@link VoUser} 用户信息
     */
    VoUser getUserByUserId(String userId);

    /**
     * 以用户昵称查询用户信息
     *
     * @param nickname 用户昵称
     * @return {@link VoUser} 用户信息列表
     */
    List<VoUser> getUserByNickname(String nickname);

    /**
     * 以用户手机号查询用户信息
     *
     * @param phone 用户手机号
     * @return {@link VoUser} 用户信息列表
     */
    List<VoUser> getUserByPhoneNumber(String phone);

    /**
     * 以用户email询用户信息
     *
     * @param email 用户手机号
     * @return {@link VoUser} 用户信息
     */
    VoUser getUserByEmail(String email);

    /**
     * 删除某位用户
     *
     * @param userId 用户标识
     * @return 是否删除成功
     */
    boolean deleteUserByUserId(String userId);

    /**
     * 添加用户
     * @param voUser 用户信息
     * @return 是否添加成功
     */
    boolean addUser(VoUser voUser);

    /**
     * 更新用户
     * @param newUser 新用户信息
     * @return 是否更新成功
     */
    boolean updateUser(VoUser newUser);
}
