package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.ArticleTag;
import com.longyu.common.service.ArticleTagService;
import com.longyu.common.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【rl_article_tag(文章标签关联表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag>
        implements ArticleTagService {

}




