package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.RoleMenu;
import com.longyu.common.service.RoleMenuService;
import com.longyu.common.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【sys_role_menu(角色和菜单关联表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
        implements RoleMenuService {

}




