package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.vo.ArticleDetailVo;
import com.longyu.common.domain.vo.ArticleListVo;
import com.longyu.common.domain.vo.HotArticleVo;
import com.longyu.common.domain.vo.PageVo;
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
    public R<PageVo<ArticleListVo>> articleList(@RequestParam Long pageNum, @RequestParam Long pageSize, @RequestParam Long categoryId) {
        return R.ok(articleService.articleList(pageNum, pageSize, categoryId));
    }

    @GetMapping("/hotArticleList")
    public R<List<HotArticleVo>> hotArticleList() {
        return R.ok(articleService.hotArticleList());
    }

    @GetMapping("/{articleId}")
    public R<ArticleDetailVo> getArticleById(@PathVariable Long articleId) {
        return R.ok(articleService.getArticleById(articleId));
    }

    @PutMapping("/updateViewCount/{articleId}")
    public R updateViewCount(@PathVariable Long articleId) {
        articleService.updateViewCount(articleId);
        return R.ok();
    }

}