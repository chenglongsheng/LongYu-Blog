package com.longyu.common.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommentListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long articleId;

    private Long rootId;
    /**
     * 所回复的目标评论的userid
     */
    private Long toCommentUserId;

    // TODO 子评论需要字段
    private String toCommentUserName;
    /**
     * 回复目标评论id
     */
    private Long toCommentId;
    /**
     * 评论内容
     */
    private String content;

    /**
     *
     */
    private Long createBy;

    /**
     *
     */
    private Date createTime;

    //TODO
    private String username;

    //TODO
    private List<CommentListVo> children;

}
