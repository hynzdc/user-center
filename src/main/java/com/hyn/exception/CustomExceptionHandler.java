package com.hyn.exception;

import com.hyn.common.ConResult;
import com.hyn.enums.ConHttpStatusEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hyn
 * @version 1.0.0
 * @description 统一异常拦截处理
 * @date 2022/10/29
 */
@RestController
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ConResult defaultExceptionHandler(Exception e){
        return ConResult.fail(ConHttpStatusEnum.FAIL);
    }
    @ExceptionHandler(BusinessException.class)
    public ConResult defaultExceptionHandler(BusinessException e){
        return ConResult.fail(e.getMessage());
    }
}
