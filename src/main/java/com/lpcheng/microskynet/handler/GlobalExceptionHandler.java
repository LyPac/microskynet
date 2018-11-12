package com.lpcheng.microskynet.handler;

import com.lpcheng.microskynet.domain.JsonResult;
import com.lpcheng.microskynet.domain.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public JsonResult exception(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        if (e instanceof NoHandlerFoundException) {
            return JsonResult.failed(ExceptionEnum.NOT_FOUND);
        }
        return JsonResult.failed(ExceptionEnum.OTHER);
    }
}
