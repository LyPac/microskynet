package com.lpcheng.microskynet.domain;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局返回的数据格式
 */
@Data
@ToString
public class JsonResult implements Serializable {

    private ExceptionEnum exceptionEnum;

    @Expose
    private int code;
    @Expose
    private String msg;
    @Expose
    private Map<String, Object> data; //返回的数据

    private JsonResult(ExceptionEnum exceptionEnum, Map<String, Object> data) {
        this.exceptionEnum = exceptionEnum;
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
        this.data = data;
    }

    public static JsonResult success(Map<String, Object> data) {
        return new JsonResult(ExceptionEnum.SUCCESS, data);
    }

    public static JsonResult failed(ExceptionEnum exceptionEnum) {
        return new JsonResult(exceptionEnum, new HashMap<>(0));
    }
}
