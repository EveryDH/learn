package com.learn.infrastructure.domain.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author hao.dai
 * @date 2019/12/15
 */
@Data
@Table(name = "l_school")
public class SchoolPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String schoolName;

    private String description;

    private String address;

    private String phone;

    private String schoolImgUrl;

    private String slideshow;

    private Date createTime;
}
