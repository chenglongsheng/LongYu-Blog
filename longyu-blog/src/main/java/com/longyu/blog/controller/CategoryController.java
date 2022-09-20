package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.vo.CategoryVo;
import com.longyu.common.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    public R<List<CategoryVo>> getCategoryList() {
        return R.ok(categoryService.getCategoryList());
    }

}
