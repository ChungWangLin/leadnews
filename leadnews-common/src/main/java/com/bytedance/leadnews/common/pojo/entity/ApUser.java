package com.bytedance.leadnews.common.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * app用户
 */
@Data
public class ApUser implements Serializable {
    private static final long serialVersionUID = 1317446078251781588L;
    private Integer id;

    private String salt;

    private String name;

    private String password;

    private String phone;

    private String image;

    private Integer sex;

    private Integer certification;

    private Integer identityAuthentication;

    private Integer status;

    private Integer flag;

    private LocalDateTime createdTime;
}
