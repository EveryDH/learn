package com.learn.service.impl;


import com.learn.infrastructure.mapper.SchoolMapper;
import com.learn.infrastructure.domain.po.SchoolPO;
import com.learn.infrastructure.request.SchoolRequest;
import com.learn.service.SchoolService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author hao.dai
 * @date 2019/12/15
 */
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public int insertSchool(SchoolRequest request) {
        SchoolPO school = new SchoolPO();
        school.setSchoolName(request.getSchoolName());
        school.setDescription(request.getDescription());
        school.setAddress(request.getAddress());
        school.setPhone(request.getPhone());
        school.setSchoolImgUrl(request.getSchoolImgUrl());
        school.setSlideshow(request.getSlideshow());
        school.setCreateTime(new Date());
        return schoolMapper.insert(school);
    }

    @Override
    public int deleteSchoolById(Integer schoolId) {
        return schoolMapper.deleteByPrimaryKey(schoolId);

    }

    @Override
    public int updateSchoolByExample(SchoolRequest request) {
        SchoolPO po = new SchoolPO();
        po.setId(request.getId());
        po.setSchoolName(request.getSchoolName());
        po.setDescription(request.getDescription());
        po.setAddress(request.getAddress());
        po.setPhone(request.getPhone());
        po.setSchoolImgUrl(request.getSchoolImgUrl());
        po.setSlideshow(request.getSlideshow());
        po.setCreateTime(request.getCreateTime());

        Example example = Example.builder(SchoolPO.class).build();
        example.and().andEqualTo("id", po.getId());
        return schoolMapper.updateByExample(po, example);
    }

    @Override
    public List<SchoolPO> selectSchoolAll(int pageIndex, int pageSize) {
        Example example = Example.builder(SchoolPO.class).build();
        return schoolMapper.selectByExampleAndRowBounds(example, new RowBounds((pageIndex - 1) * pageSize, pageSize));
    }

    @Override
    public SchoolPO selectByName(String schoolName) {
        Example example = Example.builder(SchoolPO.class).build();
        example.and().andEqualTo("schoolName", schoolName);
        return schoolMapper.selectOneByExample(example);
    }
}
