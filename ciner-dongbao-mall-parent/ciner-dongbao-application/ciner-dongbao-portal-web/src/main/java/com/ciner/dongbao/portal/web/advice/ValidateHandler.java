package com.ciner.dongbao.portal.web.advice;

import com.ciner.dongbao.common.base.result.ResultWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 请求接口返回规范报错
 */
@ControllerAdvice
public class ValidateHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        StringBuilder sb = new StringBuilder();
        for(FieldError fieldError: ex.getBindingResult().getFieldErrors()){

            String defaultMessage = fieldError.getDefaultMessage();
            sb.append("    " + defaultMessage);
            break;
        }

        return new ResponseEntity(ResultWrapper.builder().code(102).msg(sb.toString()).build() ,
                HttpStatus.OK);
    }
}
