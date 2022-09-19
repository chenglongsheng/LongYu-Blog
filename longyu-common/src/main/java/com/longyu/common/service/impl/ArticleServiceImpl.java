package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.service.ArticleService;
import com.longyu.common.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【rl_article(文章表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}




