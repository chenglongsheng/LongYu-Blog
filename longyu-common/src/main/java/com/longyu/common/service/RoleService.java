package com.longyu.common.service;

import com.longyu.common.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface RoleService extends IService<Role> {

    List<String> getRoles(Long userId);
}
