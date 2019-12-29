package com.learn.infrastructure.mapper;

import com.learn.infrastructure.domain.beans.SysRoleResources;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
public interface SysRoleResourcesMapper extends Mapper<SysRoleResources>, MySqlMapper<SysRoleResources> {
}
