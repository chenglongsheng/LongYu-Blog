package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.User;
import com.longyu.common.domain.login.UserInfo;
import com.longyu.common.service.UserService;
import com.longyu.common.mapper.UserMapper;
import com.longyu.common.util.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【sys_user(用户表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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

}




