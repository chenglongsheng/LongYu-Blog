package com.longyu.common.mapper;

import com.longyu.common.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Mapper
 * @createDate 2022-09-19 13:13:24
 * @Entity com.longyu.common.domain.entity.Menu
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> getPermsByUserId(Long id);
}




