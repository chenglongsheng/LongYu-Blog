package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.UserRole;
import com.longyu.common.service.UserRoleService;
import com.longyu.common.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements UserRoleService {

}




