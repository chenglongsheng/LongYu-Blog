package com.longyu.common.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String avatar;

    private String email;

    private String nickName;

    private Integer sex;

}
