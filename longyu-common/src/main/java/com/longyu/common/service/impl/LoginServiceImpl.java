package com.longyu.common.service.impl;

import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.LoginUser;
import com.longyu.common.domain.login.LoginUserInfo;
import com.longyu.common.domain.login.UserInfo;
import com.longyu.common.enums.AppHttpCodeEnum;
import com.longyu.common.exception.SystemException;
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
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 前台登录
     *
     * @param user
     * @return
     */
    @Override
    public LoginUserInfo login(User user) {

        LoginUser loginUser = authentication(user);
        User userUser = loginUser.getUser();

        String userId = userUser.getId().toString();
        redisCache.set(SystemConstant.BLOG_LOGIN + userId, loginUser);
        String jwt = jwtUtil.createJWT(userId);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userUser, userInfo);

        // 返回jwt给前端
        return new LoginUserInfo(jwt, userInfo);
    }

    /**
     * 后台登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Map<String, String> login(String username, String password) {

        LoginUser loginUser = authentication(new User(username, password));
        String id = loginUser.getUser().getId().toString();

        // 把登录用户信息存入redis
        redisCache.set(SystemConstant.ADMIN_LOGIN + id, loginUser);

        String jwt = jwtUtil.createJWT(id);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return map;
    }

    @Override
    public Object logout(String url) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        if (url.equals("/logout")) {
            redisCache.delete(SystemConstant.BLOG_LOGIN + userId);
        } else {
            redisCache.delete(SystemConstant.ADMIN_LOGIN + userId);
        }
        return R.ok();
    }

    /**
     * 认证
     *
     * @param user
     * @return
     */
    private LoginUser authentication(User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }

        // 使用AuthenticationManager authenticationManager验证用户名密码
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);

        // 认证不通过提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }
        return (LoginUser) authenticate.getPrincipal();
    }

}
