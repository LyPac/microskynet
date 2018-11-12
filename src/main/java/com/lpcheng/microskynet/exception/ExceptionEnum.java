package com.lpcheng.microskynet.exception;

/**
 * 异常枚举类型
 */
public enum ExceptionEnum {

    NOT_FOUND(404, "请求的url错误"),
    SUCCESS(200, "请求成功"),
    OTHER(-1,"其他错误");

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
