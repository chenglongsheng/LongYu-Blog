package com.longyu.common.service;

import com.longyu.common.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longyu.common.domain.vo.UserVo;

/**
 * @author CLS
 * @description 针对表【sys_user(用户表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface UserService extends IService<User> {

    UserVo userInfo();
}
