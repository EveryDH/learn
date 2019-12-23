package com.learn.infrastructure.domain.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author hao.dai
 * @date 2019/10/25
 */
@Data
@Table(name = "mgt_role")
public class RolePO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;
}
