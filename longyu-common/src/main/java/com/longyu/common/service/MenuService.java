package com.longyu.common.service;

import com.longyu.common.domain.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface MenuService extends IService<Menu> {

    List<String> getPermsByUserId(Long id);

    List<String> getPermissions(Long userId);
}
