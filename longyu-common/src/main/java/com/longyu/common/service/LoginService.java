package com.longyu.common.service;

import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUserInfo;

public interface LoginService {
    LoginUserInfo login(User user);

    Object logout();
}
