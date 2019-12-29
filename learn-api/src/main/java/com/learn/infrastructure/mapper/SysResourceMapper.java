package com.learn.infrastructure.mapper;
import com.learn.infrastructure.domain.beans.SysResources;
import com.learn.infrastructure.domain.vo.ResourceConditionVO;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

public interface SysResourceMapper extends Mapper<SysResources>, MySqlMapper<SysResources> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Select("SELECT " +
            " com.*, " +
            " f.id parent_id, " +
            " f.`name` parent_name, " +
            " f.`icon` parent_icon, " +
            " f.type parent_type  " +
            "FROM " +
            " sys_resources com " +
            " LEFT JOIN sys_resources f ON com.parent_id = f.id  " +
            "WHERE " +
            " 1 = 1 < IF test = \"keywords !=null and keywords != ''\" >  " +
            " AND ( " +
            " com.NAME LIKE CONCAT( " +
            " '%',#{keywords , jdbcType=VARCHAR},'%') OR " +
            " com.url LIKE CONCAT( " +
            " '%',#{keywords , jdbcType=VARCHAR},'%') OR " +
            " com.permission LIKE CONCAT( '%', #{keywords , jdbcType=VARCHAR},'%') " +
            " ) </ IF >  " +
            "ORDER BY " +
            " com.parent_id ASC, " +
            " com.NAME DESC")
    List<SysResources> findPageBreakByCondition(ResourceConditionVO vo);

    @Select("SELECT " +
            " re.id, " +
            " re.`name`, " +
            " re.parent_id, " +
            " re.url, " +
            " re.permission, " +
            " re.icon, " +
            " re.external, " +
            " re.`available`, " +
            " node.id AS node_id, " +
            " node.`name` AS node_name, " +
            " node.`type` AS node_type, " +
            " node.`url` AS node_url, " +
            " node.parent_id AS node_parent_id, " +
            " node.`permission` AS node_permission, " +
            " node.`available` AS node_available, " +
            " node.`external` AS node_external, " +
            " node.icon AS node_icon  " +
            "FROM " +
            " sys_resources re " +
            " LEFT JOIN sys_role_resources rr ON re.id = rr.resources_id " +
            " LEFT JOIN sys_user_role ur ON rr.role_id = ur.role_id " +
            " LEFT JOIN sys_resources node ON node.parent_id = re.id  " +
            " AND node.available = 1  " +
            "WHERE " +
            " ( re.parent_id = 0 OR re.parent_id IS NULL )  " +
            " AND re.available = 1  " +
            " AND ur.user_id = #{userId} " +
            " < IF test = \"type != null\" >  " +
            " AND re.type = #{type} " +
            " </ IF >  " +
            "ORDER BY " +
            " re.sort ASC, " +
            " node.sort ASC")
    List<SysResources> listUserResources(Map<String, Object> map);

    /**
     * 该节代码参考自http://blog.csdn.net/poorcoder_/article/details/71374002
     * 感谢网友
     *
     * @param rid
     * @return
     */
    @Select("SELECT " +
            " re.id, " +
            " re.`name`, " +
            " re.parent_id, " +
            " re.url, " +
            " re.type, " +
            " re.icon, " +
            " ( " +
            "CASE " +
            "  " +
            " WHEN EXISTS ( SELECT 1 FROM sys_role_resources rr WHERE rr.resources_id = re.id AND rr.role_id = #{rid} " +
            " ) THEN " +
            "  'true' ELSE 'false'  " +
            " END  " +
            "  ) AS checked  " +
            "FROM " +
            " sys_resources re  " +
            "ORDER BY " +
            "re.sort ASC")
    List<SysResources> queryResourcesListWithSelected(Long rid);

    @Select("SELECT url,permission FROM sys_resources WHERE url IS NOT NULL ORDER BY sort ASC")
    List<SysResources> listUrlAndPermission();

    @Select("SELECT " +
            "r.id, " +
            "r.`name`, " +
            "node.id AS node_id, " +
            "node.`name` AS node_name, " +
            "node.parent_id  " +
            "FROM " +
            "sys_resources r " +
            "LEFT JOIN sys_resources node ON ( node.parent_id = r.id AND node.available = 1 AND node.type = 'menu' )  " +
            "WHERE " +
            "r.available = 1  " +
            "AND r.type = 'menu'  " +
            "AND ( r.url IS NULL OR r.url = '' )  " +
            "AND ( r.permission IS NULL OR r.permission = '' )  " +
            "ORDER BY " +
            "r.sort ASC, " +
            "node.sort ASC")
    List<SysResources> listAllAvailableMenu();

    @Select("SELECT " +
            " re.id, " +
            " re.`name`  " +
            "FROM " +
            " sys_resources re  " +
            "WHERE " +
            " re.parent_id = #{pid} " +
            "  " +
            "ORDER BY " +
            " re.sort ASC")
    List<SysResources> listMenuResourceByPid(Long pid);

    @Select("SELECT " +
            " re.id, " +
            " re.`name`, " +
            " re.parent_id, " +
            " re.url, " +
            " re.permission, " +
            " re.icon, " +
            " re.sort  " +
            "FROM " +
            " sys_resources re " +
            " INNER JOIN sys_role_resources rr ON re.id = rr.resources_id " +
            " INNER JOIN sys_user_role ur ON rr.role_id = ur.role_id  " +
            "WHERE " +
            " ur.user_id = #{userId} " +
            "  " +
            " AND re.available = 1  " +
            "ORDER BY " +
            " re.parent_id ASC, " +
            " re.sort ASC")
    List<SysResources> listByUserId(Long userId);
}
