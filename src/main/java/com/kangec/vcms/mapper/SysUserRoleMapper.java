package com.kangec.vcms.mapper;
import com.kangec.vcms.entity.SysUserRole;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Ardien
 * @Date 10/8/2020 3:37 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@Component
@Mapper
public interface SysUserRoleMapper {

    @Select("select * from sys_user_role where user_id = #{userId}")
    List<SysUserRole> listByUserId(Integer userId);

    @Insert("insert into sys_user_role value(#{userId},#{roleId})")
    Long insertUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    @Update("update from sys_user_role set role_id = #{roleId} where user_id = #{userId}")
    Long updateUserRoleByUserId(SysUserRole sysUserRole);

    @Delete("delete from sys_user_role where user_id = #{userId} and role_id = #{roleId}")
    Long delete(SysUserRole sysUserRole);
}

