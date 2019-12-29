package com.learn.infrastructure.mapper;

import com.learn.infrastructure.domain.beans.SysRole;
import com.learn.infrastructure.domain.vo.RoleConditionVO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRole>, MySqlMapper<SysRole> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Select("SELECT " +
            " com.*  " +
            "FROM " +
            " sys_role com  " +
            "WHERE " +
            " 1 = 1 < IF test = \"keywords !=null and keywords != ''\" >  " +
            " AND ( " +
            " com.description LIKE CONCAT( '%', #{keywords , jdbcType=VARCHAR},'%') " +
            " ) </ IF >  " +
            "ORDER BY " +
            " com.create_time DESC")
    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    @Select("SELECT " +
            " r.id, " +
            " r.NAME, " +
            " r.description, " +
            " ( " +
            "CASE " +
            "  " +
            " WHEN ( SELECT ur.role_id FROM sys_user_role ur WHERE ur.user_id = #{userId} " +
            " AND ur.role_id = r.id ) THEN " +
            "  1 ELSE 0  " +
            " END  " +
            "  ) AS selected  " +
            "FROM " +
            " sys_role r  " +
            "WHERE " +
            "r.available = 1")
    List<SysRole> queryRoleListWithSelected(Integer userId);


    @Select("SELECT " +
            " r.id, " +
            " r.NAME, " +
            " r.description  " +
            "FROM " +
            " sys_role r " +
            " INNER JOIN sys_user_role ur ON ur.role_id = r.id  " +
            "WHERE " +
            " ur.user_id = #{userId} " +
            "  " +
            " AND r.available = 1")
    List<SysRole> listRolesByUserId(Long userId);
}
