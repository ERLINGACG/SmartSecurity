package com.erling.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;

}
