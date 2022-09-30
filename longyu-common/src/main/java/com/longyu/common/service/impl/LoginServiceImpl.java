package com.longyu.common.service.impl;

import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUser;
import com.longyu.common.domain.login.LoginUserInfo;
import com.longyu.common.domain.login.UserInfo;
import com.longyu.common.service.LoginService;
import com.longyu.common.util.JwtUtil;
import com.longyu.common.util.RedisCache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginUserInfo login(User user) {
        // 使用AuthenticationManager authenticationManager验证用户名密码
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);

        // 认证不通过提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        // 认证成功使用user生成jwt存入redis
        LoginUser authenticatedUser = (LoginUser) authenticate.getPrincipal();
        User userUser = authenticatedUser.getUser();
        String userId = userUser.getId().toString();
        redisCache.set("login:" + userId, authenticatedUser);
        String jwt = jwtUtil.createJWT(userId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userUser, userInfo);

        // 返回jwt给前端
        return new LoginUserInfo(jwt, userInfo);
    }

    @Override
    public Object logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        System.out.println(loginUser);
        String userId = loginUser.getUser().getId().toString();
        redisCache.delete("login:" + userId);
        return R.ok();
    }

}
