package com.erling.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public Result(ResultEnum e,T data){
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }
}
