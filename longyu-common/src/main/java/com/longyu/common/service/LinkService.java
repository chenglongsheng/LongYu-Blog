package com.longyu.common.service;

import com.longyu.common.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
import com.longyu.common.domain.vo.LinkListVo;

import java.util.List;

/**
 * @author CLS
 * @description 针对表【rl_link(友链)】的数据库操作Service
 * @createDate 2022-09-19 13:13:24
 */
public interface LinkService extends IService<Link> {

    List<LinkListVo> getAllLink();
}
