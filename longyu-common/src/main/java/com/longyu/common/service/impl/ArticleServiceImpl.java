package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.service.ArticleService;
import com.longyu.common.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_article(文章表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public List<Article> articleList() {
        return super.list();
    }

    @Override
    public List<Article> hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, 0);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        return page(page, queryWrapper).getRecords();
    }

    @Override
    public Article getArticleById(Long articleId) {
        return super.getById(articleId);
    }

    @Override
    public void updateViewCount(Long articleId) {
        Article article = new Article();
        article.setId(articleId);
        super.updateById(article);
    }
}




