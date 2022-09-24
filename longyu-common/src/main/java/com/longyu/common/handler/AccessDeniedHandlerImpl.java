package com.longyu.common.handler;

import com.alibaba.fastjson.JSON;
import com.longyu.common.domain.R;
import com.longyu.common.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        R<Object> r = R.ok(HttpStatus.FORBIDDEN.value(), "您的权限不足");
        String json = JSON.toJSONString(r);
        WebUtils.renderString(response, json);
    }
}