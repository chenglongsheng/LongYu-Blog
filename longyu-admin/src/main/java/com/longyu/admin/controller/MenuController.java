package com.longyu.admin.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.vo.MenuVo;
import com.longyu.common.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/getRouters")
    public R<Map<String, List<MenuVo>>> getRouters() {
        Map<String, List<MenuVo>> data = menuService.getRouters();
        return R.ok(data);
    }

}
