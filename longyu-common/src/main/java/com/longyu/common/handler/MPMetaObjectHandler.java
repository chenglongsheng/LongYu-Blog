package com.longyu.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Mybatis-plus自动填充时间
 */
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {

    public static final String createTime = "createTime";
    public static final String updateTime = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName(createTime, now, metaObject);
        this.setFieldValByName(updateTime, now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName(updateTime, now, metaObject);
    }
}
