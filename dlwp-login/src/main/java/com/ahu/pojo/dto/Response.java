package com.ahu.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int status;
    private String message;
    private T data;


    public Response<T> success(T data){
        this.data = data;
        this.status = 200;
        this.message = "ok";
        return this;
    }

    public Response<T> error(int status,T data){
        this.data = data;
        this.status = status;
        this.message = "fail";
        return this;
    }
}
