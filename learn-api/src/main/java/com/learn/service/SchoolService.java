package com.learn.service;

import com.learn.infrastructure.domain.po.SchoolPO;
import com.learn.infrastructure.request.SchoolRequest;

import java.util.List;

/**
 * @author hao.dai
 * @date 2019/12/15
 */


public interface SchoolService {
    
    int insertSchool(SchoolRequest request);

    int deleteSchoolById(Integer schoolId);

    int updateSchoolByExample(SchoolRequest request);

    List<SchoolPO> selectSchoolAll(int pageIndex, int pageSize);

    SchoolPO selectByName(String username);
    
}
