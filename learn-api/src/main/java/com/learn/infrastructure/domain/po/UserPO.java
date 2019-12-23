package com.learn.infrastructure.domain.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hao.dai
 * @date 2019/8/18
 */
@Data
@Table(name = "l_user")
public class UserPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String openId;

    private String imgUrl;

    private Integer sex;

    private String nickName;

    private Integer status;

    private String city;

    private Date createTime;
}
