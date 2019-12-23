package com.learn.infrastructure.mapper;

import com.learn.infrastructure.domain.po.SchoolPO;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author hao.dai
 * @date 2019/12/15
 */
public interface SchoolMapper extends Mapper<SchoolPO>, MySqlMapper<SchoolPO> {
}
