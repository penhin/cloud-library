package com.maijianda.cloud_library.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.maijianda.cloud_library.common.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException e) {
        return Result.fail(e.getMessage());
    }
    
    @ExceptionHandler(Exception.class) 
    public Result<Void> handleException(Exception e) {
        e.printStackTrace();
        return Result.fail("系统异常，请稍后重试");
    }
}
