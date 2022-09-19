package com.longyu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.longyu.common.domain.entity.Tag;
import com.longyu.common.service.TagService;
import com.longyu.common.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
 * @author CLS
 * @description 针对表【rl_tag(标签)】的数据库操作Service实现
 * @createDate 2022-09-19 13:13:24
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
        implements TagService {

}




