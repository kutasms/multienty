package com.chia.multienty.core.exception;

import lombok.Getter;

@Getter
public class HttpException extends RuntimeException{
    private Integer statusCode;

    private Integer errorCode;
    private String message;

    public HttpException(Integer statusCode, Integer errorCode, String message,Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }

    public HttpException(Integer statusCode, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.errorCode = statusCode;
        this.message = message;
    }
}
