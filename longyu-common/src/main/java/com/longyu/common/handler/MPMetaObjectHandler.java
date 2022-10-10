package com.longyu.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.longyu.common.util.SecurityUtil;
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
    public static final String createBy = "createBy";
    public static final String updateBy = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        Long userId = null;
        try {
            userId = SecurityUtil.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = 1L;//表示是自己创建
        }
        this.setFieldValByName(createTime, now, metaObject);
        this.setFieldValByName(createBy, userId, metaObject);
        this.setFieldValByName(updateTime, now, metaObject);
        this.setFieldValByName(updateBy, userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName(updateTime, now, metaObject);
        this.setFieldValByName(updateBy, SecurityUtil.getUserId(), metaObject);
    }
}
