package com.ahu.exception;


import com.ahu.pojo.dto.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    //最大异常捕获器
    @ExceptionHandler(Exception.class)
    public Response<String> exceptionHandle(Exception e){
        e.printStackTrace();
        return new Response<String>().error(500,"出现异常");
    }
}
