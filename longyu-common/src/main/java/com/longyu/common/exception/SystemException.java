package com.longyu.common.exception;

import com.longyu.common.enums.AppHttpCodeEnum;

public class SystemException extends RuntimeException {

    private final Integer code;
    private final String message;

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        this.code = httpCodeEnum.getCode();
        this.message = httpCodeEnum.getMsg();
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
