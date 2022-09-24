package com.longyu.common.handler;

import com.longyu.common.domain.R;
import com.longyu.common.enums.AppHttpCodeEnum;
import com.longyu.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public R systemExceptionHandler(SystemException e) {
        //打印异常信息
        log.error("出现了异常！ {}", e.getMessage());
        //从异常对象中获取提示信息封装返回
        return R.fail(e.getCode(), e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception e) {
        //打印异常信息
        log.error("出现了异常！ {}", e.getMessage());
        //从异常对象中获取提示信息封装返回
        return R.fail(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), e.getMessage());
    }
}