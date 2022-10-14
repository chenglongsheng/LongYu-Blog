package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.entity.Menu;
import com.longyu.common.domain.vo.MenuVo;
import com.longyu.common.service.MenuService;
import com.longyu.common.mapper.MenuMapper;
import com.longyu.common.util.SecurityUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
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

    @Override
    public Map<String, List<MenuVo>> getRouters() {
        Map<String, List<MenuVo>> map = new HashMap<>();

        Long userId = SecurityUtil.getUserId();

        List<MenuVo> menuTree;

        // 超级管理员 获取所有
        if (userId == 1L) {
            QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().in(Menu::getMenuType, SystemConstant.CATEGORY, SystemConstant.MENU)
                    .eq(Menu::getStatus, SystemConstant.STATUS_NORMAL);
            List<Menu> menus = super.list(queryWrapper);
            menuTree = getMenuVos(menus);

        } else {
            List<Menu> menus = baseMapper.getRoutersByUserId(userId);
            menuTree = getMenuVos(menus);
        }

        map.put("menus", menuTree);
        return map;
    }

    @NotNull
    private List<MenuVo> getMenuVos(List<Menu> menus) {
        List<MenuVo> menuTree;
        menuTree = menus.stream().map(menu -> {
                    MenuVo menuVo = new MenuVo();
                    BeanUtils.copyProperties(menu, menuVo);
                    return menuVo;
                }).filter(menuVo -> menuVo.getParentId() == 0)
                .peek(menuVo -> menuVo.setChildren(getChildren(menuVo, menus)))
                .sorted(Comparator.comparing(MenuVo::getOrderNum))// 升序
                .collect(Collectors.toList());
        return menuTree;
    }

    private List<MenuVo> getChildren(MenuVo menuVo, List<Menu> menus) {
        return menus.stream().filter(menu -> menu.getParentId().equals(menuVo.getId()))
                .map(menu -> {
                    MenuVo vo = new MenuVo();
                    BeanUtils.copyProperties(menu, vo);
                    return vo;
                })
                .sorted(Comparator.comparing(MenuVo::getOrderNum))
                .collect(Collectors.toList());
    }

}





