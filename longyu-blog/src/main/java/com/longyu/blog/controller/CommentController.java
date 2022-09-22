package com.longyu.blog.controller;

import com.longyu.common.domain.R;
import com.longyu.common.domain.entity.Comment;
import com.longyu.common.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    public R<List<Comment>> commentList(@RequestParam Long pageNum, @RequestParam Long pageSize, @RequestParam Long articleId) {
        return R.ok(commentService.commentList(pageNum, pageSize, articleId));
    }

}
