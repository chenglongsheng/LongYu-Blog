package com.longyu.common.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<T> rows;
    private Long total;
}
