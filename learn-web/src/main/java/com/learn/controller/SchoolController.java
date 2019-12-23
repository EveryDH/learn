package com.learn.controller;


import com.learn.infrastructure.domain.po.SchoolPO;
import com.learn.request.vo.CommonReturnType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.learn.infrastructure.request.SchoolRequest;
import com.learn.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hao.dai
 * @date 2019/8/18
 */
@Api(description = "API", tags = "/school")
@CrossOrigin(allowCredentials = "true",maxAge = 3600)
@RestController
@RequestMapping(path = "/school", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class SchoolController {

    @Autowired
    SchoolService schoolService;

    @PostMapping("/saveSchool")
    @ApiOperation(value = "添加学校")
    public CommonReturnType saveSchool(@RequestBody SchoolRequest request) {
        int ret = schoolService.insertSchool(request);
        return new CommonReturnType<>(true,"请求成功",ret);
    }

    @GetMapping("/deleteSchool")
    @ApiOperation(value = "根据 学校id删除该学校")
    public CommonReturnType deleteUserById(Integer userId) {
        int ret = schoolService.deleteSchoolById(userId);
        return new CommonReturnType<>(true,"请求成功",ret);
    }

    @PostMapping("/updateSchool")
    @ApiOperation(value = "根据 学校id修改学校信息")
    public CommonReturnType updateUserInfo(@RequestBody SchoolRequest request) {
        int ret = schoolService.updateSchoolByExample(request);
        return new CommonReturnType<>(true,"请求成功",ret);
    }

    @GetMapping("/getSchoolAll")
    @ApiOperation(value = "获取所有的学校信息")
    public CommonReturnType getUserAll(int pageIndex, int pageSize) {
        List<SchoolPO> ret = schoolService.selectSchoolAll(pageIndex, pageSize);
        return new CommonReturnType<>(true,"请求成功",ret);
    }

    @GetMapping("/index")
    @ApiOperation(value = "index")
    public String index() {
        return "index";
    }
}
