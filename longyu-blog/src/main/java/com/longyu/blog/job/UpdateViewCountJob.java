package com.longyu.blog.job;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.service.ArticleService;
import com.longyu.common.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    /**
     * 每十分钟更新数据
     */
    @Scheduled(cron = "* 10 * * * ?")
    @Transactional
    public void updateViewCount() {
        // 从缓存获取浏览量
        Map<String, Integer> map = redisCache.getMap("article:viewCount");
        //更新数据库
        List<Article> articles = map.entrySet().stream().map(item -> new Article(Long.valueOf(item.getKey()), Long.valueOf(item.getValue()))).collect(Collectors.toList());
        for (Article article : articles) {
            LambdaUpdateWrapper<Article> wrapper = Wrappers.lambdaUpdate(Article.class).eq(Article::getId, article.getId()).set(Article::getViewCount, article.getViewCount());
            articleService.update(wrapper);
        }

    }

}
