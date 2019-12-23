package com.learn.infrastructure.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hao.dai
 * @date 2019/12/15
 */
@Data
public class SchoolRequest {

    @ApiModelProperty(value = "学校id")
    private Integer id;

    @ApiModelProperty(value = "学校名字")
    private String schoolName;

    @ApiModelProperty(value = "学校简介")
    private String description;

    @ApiModelProperty(value = "学校地址")
    private String address;

    @ApiModelProperty(value = "学校联系人电话")
    private String phone;

    @ApiModelProperty(value = "轮播图")
    private String slideshow;

    @ApiModelProperty(value = "学校头像URL地址")
    private String schoolImgUrl;

    @ApiModelProperty(value = "学校创建时间")
    private Date createTime;
}
