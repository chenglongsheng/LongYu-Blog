package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.entity.Menu;
import com.longyu.common.service.MenuService;
import com.longyu.common.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CLS
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> getPermsByUserId(Long id) {

        // 超级管理员
        if (id == 1L) {
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().in(Menu::getMenuType, Arrays.asList(SystemConstant.MENU, SystemConstant.BUTTON)
            ).eq(Menu::getStatus, SystemConstant.STATUS_NORMAL);
            return super.list(queryWrapper).stream().map(Menu::getPerms).collect(Collectors.toList());
        }

        return super.baseMapper.getPermsByUserId(id);
    }

    @Override
    public List<String> getPermissions(Long userId) {
        return getPermsByUserId(userId);
    }
}




