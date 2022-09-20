package com.longyu.common.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LinkListVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 链接名称
     */
    private String name;

    /**
     * logo
     */
    private String logo;

    /**
     * 描述
     */
    private String description;

    /**
     * 网站地址
     */
    private String address;

}
