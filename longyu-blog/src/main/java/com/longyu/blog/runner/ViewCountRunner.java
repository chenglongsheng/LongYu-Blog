package com.longyu.blog.runner;

import com.longyu.common.service.ArticleService;
import com.longyu.common.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Integer> map = articleService.list().stream().collect(Collectors.toMap(
                article -> article.getId().toString(), article -> article.getViewCount().intValue()
        ));
        redisCache.setMap("article:viewCount", map);
    }
}
