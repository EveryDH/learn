package com.learn.infrastructure.mapper;

import com.learn.infrastructure.domain.beans.SysUser;
import com.learn.infrastructure.domain.vo.UserConditionVO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
public interface SysUserMapper extends Mapper<SysUser>, MySqlMapper<SysUser> {

    @Select("SELECT " +
            "s.*  " +
            "FROM " +
            "sys_user s  " +
            "WHERE " +
            "1 = 1 < IF test = \"keywords != null and keywords != '' \" >  " +
            "AND ( " +
            "s.nickname LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.mobile LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.username LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.PASSWORD LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.email LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.qq LIKE CONCAT( " +
            "'%',#{keywords , jdbcType=VARCHAR},'%') or " +
            "s.remark LIKE CONCAT( '%', #{keywords , jdbcType=VARCHAR},'%') or " +
            ") </ IF > <! -- 查询用户信息 --> " +
            "< IF test = \"user != null\" > < IF test = \"user.id!=null\" >  " +
            "AND s.id = #{user.id} " +
            "</ IF > < IF test = \"user.gender!=null\" >  " +
            "AND s.gender = #{user.gender} " +
            "</ IF > < IF test = \"user.userType!=null\" >  " +
            "AND s.user_type = #{user.userType} " +
            "</ IF > < IF test = \"user.username!=null\" >  " +
            "AND s.username = #{user.username} " +
            "</ IF > < IF test = \"user.password!=null\" >  " +
            "AND s.PASSWORD = #{user.password} " +
            "</ IF > < IF test = \"user.lastLoginIp!=null\" >  " +
            "AND s.last_login_ip = #{user.lastLoginIp} " +
            "</ IF > < IF test = \"user.lastLoginTime!=null\" >  " +
            "AND s.last_login_time = #{user.lastLoginTime} " +
            "</ IF > </ IF >  " +
            "GROUP BY " +
            "s.id  " +
            "ORDER BY " +
            "s.create_time DESC")
    List<SysUser> findPageBreakByCondition(UserConditionVO vo);


    @Select("SELECT " +
            "s.id, " +
            "s.username, " +
            "s.nickname  " +
            "FROM " +
            "sys_user s " +
            "INNER JOIN sys_user_role sur ON sur.user_id = s.id  " +
            "WHERE " +
            "sur.role_id = #{roleId}")
    List<SysUser> listByRoleId(Long roleId);

}
