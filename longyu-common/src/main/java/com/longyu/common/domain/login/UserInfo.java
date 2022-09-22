package com.longyu.common.domain.login;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String nickName;

    private Integer sex;

    private String email;

    private String avatar;

}
