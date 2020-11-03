package com.kangec.vcms.mapper;
import com.kangec.vcms.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author Ardien
 * @Date 10/8/2020 1:17 PM
 * @Email ardien@126.com
 * @Version 1.0
 **/

@Component
@Mapper
public interface SysUserMapper {

    /**
     * 查询用户
     *
     * @param username The username
     * @return The User Entity
     */
    SysUser queryUserByUsername(String username);

    /**
     * 查询用户
     * @param id the User id
     * @return SysUser
     */
    SysUser queryUserById(String id);

    /**
     * 添加用户
     * @param sysUser 待添加的用户
     * @return 添加成功后所返回的用户
     */
    long insertUser(SysUser sysUser);

    /**
     * 更新用户信息
     * @param sysUser 待更新的用户
     * @return 受影响的行数
     */
    Integer updateUser(SysUser sysUser);

    /**
     * 清除用户
     *
     * @param userId 待删除的用户id
     * @return 受影响的行数
     */
    Integer deleteUser(String userId);
}
