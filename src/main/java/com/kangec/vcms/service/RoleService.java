package com.kangec.vcms.service;

import com.kangec.vcms.controller.vo.VoRole;

import java.util.List;

/**
 * 角色服务公共接口
 *
 * @author PC
 * */
public interface RoleService {
    /**
     * 查询所有角色信息
     * @return {@link VoRole} 列表
     */
    List<VoRole> getRoleList();

    /**
     * 查询某个角色信息
     * @param roleId role id
     * @return  符合 {@link VoRole} 的对象信息
     */
    VoRole getRoleByRoleId(String roleId);

    /**
     * 添加角色
     * @param voRole 角色 {@link VoRole} 信息
     * @return 是否添加成功
     */
    boolean addRole(VoRole voRole);

    /**
     * 删除角色
     * @param voRole 角色 {@link VoRole} 信息
     * @return 是否删除成功
     */
    boolean deleteRole(VoRole voRole);

    /**
     * 更新角色
     * @param voRole 角色 {@link VoRole} 信息
     * @return 是否更新成功
     */
    boolean updateRole(VoRole voRole);
}
