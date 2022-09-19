package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Link;
import com.longyu.common.service.LinkService;
import com.longyu.common.mapper.LinkMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【rl_link(友链)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link>
        implements LinkService {

}




