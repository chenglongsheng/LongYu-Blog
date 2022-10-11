package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.UserInfo;
import com.longyu.common.enums.AppHttpCodeEnum;
import com.longyu.common.exception.SystemException;
import com.longyu.common.service.UserService;
import com.longyu.common.mapper.UserMapper;
import com.longyu.common.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author CLS
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo userInfo() {
        Long userId = SecurityUtil.getUserId();
        User user = super.getById(userId);
        UserInfo userVo = new UserInfo();
        BeanUtils.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        return super.updateById(user);
    }

    @Override
    public Boolean register(User user) {

        //对数据进行非空判断
        if (!StringUtils.hasText(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getPassword())) {
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if (!StringUtils.hasText(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        }
        //对数据进行是否存在的判断
        if (userNameExist(user.getUsername())) {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(user.getNickName())) {
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        //对密码进行加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return super.save(user);
    }

    private boolean nickNameExist(String nickName) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getNickName, nickName);
        return super.count(wrapper) > 0;
    }

    private boolean userNameExist(String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class).eq(User::getUsername, username);
        return super.count(wrapper) > 0;
    }

}




