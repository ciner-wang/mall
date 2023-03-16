package com.ciner.dongbao.portal.web.advice;

import com.ciner.dongbao.common.base.result.ResultWrapper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理异常不使用try catch
 */
//@ControllerAdvice
//@RestController   如果不添加rest找不到页面就会报错
@RestControllerAdvice
public class GlobalExceptionHandle {

    //精细异常，单向捕捉，在程序报错的异常中。捕捉
    @ExceptionHandler(ArithmeticException.class)
    public ResultWrapper customException() {
        return ResultWrapper.builder().code(301).msg("统一异常").build();
    }
}
