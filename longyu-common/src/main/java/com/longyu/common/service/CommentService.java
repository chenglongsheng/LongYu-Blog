package com.longyu.common.service;

import com.longyu.common.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longyu.common.domain.vo.CommentListVo;
import com.longyu.common.domain.vo.PageVo;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_comment(评论表)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface CommentService extends IService<Comment> {

    PageVo<CommentListVo> commentList(Long pageNum, Long pageSize, Long articleId);
}
