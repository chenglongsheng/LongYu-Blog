package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.domain.entity.Category;
import com.longyu.common.domain.vo.ArticleDetailVo;
import com.longyu.common.domain.vo.ArticleListVo;
import com.longyu.common.domain.vo.HotArticleVo;
import com.longyu.common.domain.vo.PageVo;
import com.longyu.common.service.ArticleService;
import com.longyu.common.mapper.ArticleMapper;
import com.longyu.common.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author CLS
 * @description 针对表【rl_article(文章表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private CategoryService categoryService;

    @Override
    public PageVo<ArticleListVo> articleList(Long pageNum, Long pageSize, Long categoryId) {
        Page<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = Wrappers.lambdaQuery(Article.class)
                .eq(categoryId != 0, Article::getCategoryId, categoryId)
                .eq(Article::getStatus, SystemConstant.STATUS_NORMAL);
        Page<Article> articlePage = super.page(page, queryWrapper);
        List<ArticleListVo> articleListVos = articlePage.getRecords().stream().map(item -> {
            ArticleListVo articleListVo = new ArticleListVo();
            BeanUtils.copyProperties(item, articleListVo);
            return articleListVo;
        }).collect(Collectors.toList());
        return new PageVo<>(articleListVos, articlePage.getTotal());
    }

    @Override
    public List<HotArticleVo> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, SystemConstant.ARTICLE_STATUS_PUBLISHED);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(SystemConstant.PAGE_CURRENT, SystemConstant.PAGE_SIZE);
        List<Article> records = page(page, queryWrapper).getRecords();
        return records.stream().map(item -> new HotArticleVo(item.getId(), item.getTitle(), item.getViewCount())).collect(Collectors.toList());
    }

    @Override
    public ArticleDetailVo getArticleById(Long articleId) {
        Article article = super.getById(articleId);
        ArticleDetailVo articleDetailVo = new ArticleDetailVo();
        BeanUtils.copyProperties(article, articleDetailVo);
        Long categoryId = article.getCategoryId();
        if (categoryId != null) {
            Category category = categoryService.getById(categoryId);
            articleDetailVo.setCategoryName(category.getName());
        }
        return articleDetailVo;
    }

    @Override
    public void updateViewCount(Long articleId) {
        Article article = new Article();
        article.setId(articleId);
        super.updateById(article);
    }
}




