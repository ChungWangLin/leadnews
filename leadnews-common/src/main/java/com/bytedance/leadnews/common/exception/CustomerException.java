package com.bytedance.leadnews.common.exception;

public class CustomerException extends RuntimeException{
    private final String message;
    public CustomerException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
