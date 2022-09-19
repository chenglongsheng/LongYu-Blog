package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Comment;
import com.longyu.common.service.CommentService;
import com.longyu.common.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【rl_comment(评论表)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

}




