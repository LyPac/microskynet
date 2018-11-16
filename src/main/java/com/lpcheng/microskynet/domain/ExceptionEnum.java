package com.lpcheng.microskynet.domain;

/**
 * 异常枚举类型
 */
public enum ExceptionEnum {

    NOT_FOUND(404, "请求的url错误"),
    SUCCESS(200, "请求成功"),
    OTHER(-1,"服务器错误");

    private int code;
    private String msg;

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
