package com.longyu.common.domain;

import com.longyu.common.enums.AppHttpCodeEnum;

import java.io.Serializable;

/**
 * 统一返回结果
 *
 * @param <T>
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;

    private R() {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
    }

    private R(T data) {
        this.code = AppHttpCodeEnum.SUCCESS.getCode();
        this.msg = AppHttpCodeEnum.SUCCESS.getMsg();
        this.data = data;
    }

    private R(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private R(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(Integer code, T data) {
        return new R<>(code, data);
    }

    public static <T> R<T> ok(Integer code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> ok(T data) {
        return new R<>(data);
    }

    public static <T> R<T> ok(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public static <T> R<T> fail() {
        return new R<>(AppHttpCodeEnum.SYSTEM_ERROR.getCode(), AppHttpCodeEnum.SYSTEM_ERROR.getMsg());
    }

    public static <T> R<T> fail(Integer code, String msg) {
        return new R<>(code, msg);
    }

    public static <T> R<T> fail(Integer code, String msg, T data) {
        return new R<>(code, msg, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}