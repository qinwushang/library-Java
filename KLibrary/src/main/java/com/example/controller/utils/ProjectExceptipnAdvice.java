package com.example.controller.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//异常处理
@RestControllerAdvice
public class ProjectExceptipnAdvice {

    @ExceptionHandler
    public R doException(Exception ex){
        ex.printStackTrace();
        return new R("服务器错误，请重试");
    }
}
