package com.kangec.vcms.mapper;
import com.kangec.vcms.entity.SysRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author Ardien
 * @Date 10/8/2020 3:35 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@Component
@Mapper
public interface SysRoleMapper {

    /**
     * 查询角色
     *
     * @param roleId the role id
     * @return 系统角色
     */
    SysRole queryById(Integer roleId);

    /**
     * 添加角色
     *
     * @param sysRole 待添加角色
     * @return 受影响的行数
     */
    Long insertRole(SysRole sysRole);

    /**
     * 更新角色信息
     *
     * @param sysRole 待更新角色
     * @return 受影响的行数
     */
    Long updateRole(SysRole sysRole);

    /**
     * 删除指定角色
     * @param roleId the role_id
     * @return 受影响的行数
     */
    Long deleteRole(Integer roleId);
}
