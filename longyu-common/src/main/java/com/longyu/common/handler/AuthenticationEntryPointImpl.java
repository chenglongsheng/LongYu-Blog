package com.longyu.common.handler;

import com.alibaba.fastjson.JSON;
import com.longyu.common.domain.R;
import com.longyu.common.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证处理器
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        R<Object> r = R.ok(HttpStatus.UNAUTHORIZED.value(), "用户认证失败请重新登录");
        String json = JSON.toJSONString(r);
        WebUtils.renderString(response, json);
    }
}