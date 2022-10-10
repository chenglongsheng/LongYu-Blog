package com.longyu.blog;

import com.longyu.common.domain.vo.CommentListVo;
import com.longyu.common.domain.vo.PageVo;
import com.longyu.common.service.CommentService;
import com.longyu.common.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class LongyuBlogApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {

        String encode = passwordEncoder.encode("123");
        System.out.println(encode);

    }

    @Test
    void testCommentList() {
        PageVo<CommentListVo> commentListVoPageVo = commentService.commentList(1L, 4L, 1L);
        System.out.println(commentListVoPageVo);
    }

}
