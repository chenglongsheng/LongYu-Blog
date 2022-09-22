package com.longyu.common.domain.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    private UserInfo userInfo;

}
