package com.longyu.common.service;

import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUserInfo;

import java.util.Map;

public interface LoginService {

    LoginUserInfo login(User user);

    Object logout(String url);

    Map<String, String> login(String username, String password);
}
