package com.zhenhui.demo.falcon.core.surport.exception;

public class DecodeException extends Exception {

    public DecodeException(String message) {
        super(message);
    }

    public DecodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecodeException(Throwable cause) {
        super(cause);
    }
}
