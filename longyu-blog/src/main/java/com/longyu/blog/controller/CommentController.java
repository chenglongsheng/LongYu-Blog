package com.longyu.blog.controller;

import com.longyu.common.constont.SystemConstant;
import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.Comment;
import com.longyu.common.domain.vo.CommentListVo;
import com.longyu.common.domain.vo.PageVo;
import com.longyu.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public R comment(@RequestBody Comment comment) {
        commentService.comment(comment);
        return R.ok();
    }

    @GetMapping("/commentList")
    public R<PageVo<CommentListVo>> commentList(@RequestParam Long pageNum, @RequestParam Long pageSize, @RequestParam Long articleId) {
        return R.ok(commentService.commentList(SystemConstant.ARTICLE_COMMENT, pageNum, pageSize, articleId));
    }

    @GetMapping("/linkCommentList")
    public R<PageVo<CommentListVo>> getLinkComment(@RequestParam Long pageNum, @RequestParam Long pageSize) {
        return R.ok(commentService.commentList(SystemConstant.LINK_COMMENT, pageNum, pageSize, null));
    }

}
