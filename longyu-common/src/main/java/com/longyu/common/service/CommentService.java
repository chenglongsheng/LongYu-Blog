package com.longyu.common.service;

import com.longyu.common.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_comment(评论表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface CommentService extends IService<Comment> {

    List<Comment> commentList(Long pageNum, Long pageSize, Long articleId);
}
