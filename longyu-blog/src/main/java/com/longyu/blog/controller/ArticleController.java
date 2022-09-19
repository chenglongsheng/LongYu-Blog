package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 查询文章列表
     *
     * @return 文章列表
     */
    @GetMapping("/articleList")
    public R<List<Article>> articleList() {
        return R.ok(articleService.articleList());
    }

    @GetMapping("/hotArticleList")
    public R<List<Article>> hotArticleList() {
        return R.ok(articleService.hotArticleList());
    }

    @GetMapping("/getArticle/{articleId}")
    public R<Article> getArticleById(@PathVariable Long articleId) {
        return R.ok(articleService.getArticleById(articleId));
    }

    @PutMapping("/updateViewCount/{articleId}")
    public R updateViewCount(@PathVariable Long articleId) {
        articleService.updateViewCount(articleId);
        return R.ok();
    }

}