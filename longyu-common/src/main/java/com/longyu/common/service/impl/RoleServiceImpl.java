package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Role;
import com.longyu.common.service.RoleService;
import com.longyu.common.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【sys_role(角色信息表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




