package com.bytedance.leadnews.common.exception;

public class ParamRequestException extends RuntimeException{
    private final String message;

    public ParamRequestException() {
        this.message = "参数不合法";
    }

    public ParamRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
