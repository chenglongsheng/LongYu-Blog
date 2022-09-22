package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Comment;
import com.longyu.common.service.CommentService;
import com.longyu.common.mapper.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_comment(评论表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<Comment> commentList(Long pageNum, Long pageSize, Long articleId) {
        return null;
    }

}




