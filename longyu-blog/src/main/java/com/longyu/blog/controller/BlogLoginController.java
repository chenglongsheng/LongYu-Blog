package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUserInfo;
import com.longyu.common.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public R<LoginUserInfo> login(@RequestBody User user) {
        return R.ok(loginService.login(user));
    }

    @PostMapping("/logout")
    public R logout() {
        return R.ok(loginService.logout("/logout"));
    }

}
