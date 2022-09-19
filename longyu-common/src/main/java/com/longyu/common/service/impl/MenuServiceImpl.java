package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Menu;
import com.longyu.common.service.MenuService;
import com.longyu.common.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【sys_menu(菜单权限表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

}




