package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.entity.Article;
import com.longyu.common.domain.entity.Comment;
import com.longyu.common.domain.vo.CommentListVo;
import com.longyu.common.domain.vo.PageVo;
import com.longyu.common.enums.AppHttpCodeEnum;
import com.longyu.common.exception.SystemException;
import com.longyu.common.service.ArticleService;
import com.longyu.common.service.CommentService;
import com.longyu.common.mapper.CommentMapper;
import com.longyu.common.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author CLS
 * @description 针对表【rl_comment(评论表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Override
    public PageVo<CommentListVo> commentList(String commentType, Long pageNum, Long pageSize, Long articleId) {

        Page<Comment> commentPage = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(articleId != null, Comment::getArticleId, articleId)
                .eq(Comment::getType, commentType)
                .eq(Comment::getRootId, -1)
                .orderByDesc(Comment::getCommonCount, Comment::getCreateTime);

        Page<Comment> page = super.page(commentPage, queryWrapper);
        List<Comment> records = page.getRecords();
        List<CommentListVo> result = records.stream().map(comment -> {
            CommentListVo vo = new CommentListVo();
            BeanUtils.copyProperties(comment, vo);
            String username = userService.getById(comment.getCreateBy()).getUsername();
            vo.setUsername(username);
            List<CommentListVo> children = getChildren(comment.getId());
            vo.setChildren(children);
            return vo;
        }).collect(Collectors.toList());
        return new PageVo<>(result, page.getTotal());
    }

    @Override
    public void comment(Comment comment) {
        if (!StringUtils.hasText(comment.getContent())) {
            throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        }
        // 查询文章是否开启评论
        Article article = articleService.getById(comment.getArticleId());
        if (article == null) {
            throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        if (!article.getIsComment().equals(SystemConstant.IS_COMMENT)) {
            throw new SystemException(AppHttpCodeEnum.CAN_NOT_COMMENT);
        }
        super.save(comment);
    }

    private List<CommentListVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> wrapper = Wrappers.lambdaQuery(Comment.class)
                .eq(Comment::getRootId, id)
                .orderByDesc(Comment::getCreateTime);
        List<Comment> comments = super.list(wrapper);
        return comments.stream().map(comment -> {
            CommentListVo vo = new CommentListVo();
            BeanUtils.copyProperties(comment, vo);
            String username = userService.getById(comment.getToCommentUserId()).getUsername();
            vo.setToCommentUserName(username);
            return vo;
        }).collect(Collectors.toList());
    }

}




