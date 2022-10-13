package com.longyu.common.domain.vo;

import com.longyu.common.domain.login.UserInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AdminUserInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> permissions;

    private List<String> roles;

    private UserInfo user;

}
