package com.longyu.admin.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUserInfo;
import com.longyu.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AdminLoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public R login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String, String> login = loginService.login(username, password);
        return R.ok(login);
    }

    @PostMapping("/user/logout")
    public R logout() {
        return R.ok(loginService.logout("/user/logout"));
    }

}
