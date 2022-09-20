package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.Category;
import com.longyu.common.domain.vo.CategoryVo;
import com.longyu.common.service.CategoryService;
import com.longyu.common.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CLS
 * @description 针对表【rl_category(分类表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<CategoryVo> getCategoryList() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getPid, 0)
                .eq(Category::getStatus, SystemConstant.STATUS_NORMAL);
        return super.list(queryWrapper).stream().map(item -> {
            CategoryVo categoryVo = new CategoryVo();
            BeanUtils.copyProperties(item, categoryVo);
            return categoryVo;
        }).collect(Collectors.toList());
    }
}




