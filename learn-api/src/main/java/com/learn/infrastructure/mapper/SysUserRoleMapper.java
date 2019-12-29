package com.learn.infrastructure.mapper;
import com.learn.infrastructure.domain.beans.SysUserRole;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
public interface SysUserRoleMapper extends Mapper<SysUserRole>, MySqlMapper<SysUserRole> {

    @Select("SELECT " +
            "user_id " +
            "FROM " +
            "sys_user_role " +
            "WHERE " +
            "role_id = #{roleId} ")
    List<Integer> findUserIdByRoleId(Integer roleId);
}
