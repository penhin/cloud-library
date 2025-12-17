package com.maijianda.cloud_library.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class Result<T> implements Serializable {

    private boolean success;
    private String message;
    private T data;
    
    public Result() {
    }

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
    
    public static <T> Result<T> success() {
        return new Result<T>(true, "Operation successful", null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, "Operation successful", data);
    }
    
    public static <T> Result<T> success(String message, T data) {
        return new Result<T>(true, message, data);
    }
    
    public static <T> Result<T> fail(String message) {
        return new Result<T>(false, message, null);
    }

    public static <T> Result<T> fail(String message, T data) {
        return new Result<T>(false, message, data);
    }
}
