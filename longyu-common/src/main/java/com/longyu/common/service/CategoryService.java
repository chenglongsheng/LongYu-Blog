package com.longyu.common.service;

import com.longyu.common.domain.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longyu.common.domain.vo.CategoryVo;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_category(分类表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface CategoryService extends IService<Category> {

    List<CategoryVo> getCategoryList();
}
