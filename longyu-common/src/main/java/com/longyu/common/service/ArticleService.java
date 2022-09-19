package com.longyu.common.service;

import com.longyu.common.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longyu.common.domain.vo.HotArticleVo;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_article(文章表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface ArticleService extends IService<Article> {

    List<Article> articleList(Long pageNum, Long pageSize, Long categoryId);

    List<HotArticleVo> hotArticleList();

    Article getArticleById(Long articleId);

    void updateViewCount(Long articleId);
}
