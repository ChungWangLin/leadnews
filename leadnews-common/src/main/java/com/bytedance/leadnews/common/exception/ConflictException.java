package com.bytedance.leadnews.common.exception;

public class ConflictException extends RuntimeException{
    private final String message;
    public ConflictException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
