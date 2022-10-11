package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.login.UserInfo;
import com.longyu.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userInfo")
    public R<UserInfo> userInfo() {
        UserInfo user = userService.userInfo();
        return R.ok(user);
    }

    @PutMapping("/userInfo")
    public R<Boolean> updateUserInfo(@RequestBody UserInfo userInfo) {
        Boolean b = userService.updateUserInfo(userInfo);
        return b ? R.ok() : R.fail();
    }

}
