package com.example.meajude.exceptions.ApiExceptions;

public class ApiException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final String msg;
    private final int code;

    public ApiException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

}
