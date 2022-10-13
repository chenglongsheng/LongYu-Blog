package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Role;
import com.longyu.common.domain.entity.UserRole;
import com.longyu.common.service.RoleService;
import com.longyu.common.mapper.RoleMapper;
import com.longyu.common.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CLS
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<String> getRoles(Long userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRole::getUserId, userId);
        List<Long> roleIds = userRoleService.list(queryWrapper).stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return super.listByIds(roleIds).stream().map(Role::getRoleKey).collect(Collectors.toList());
    }

}




