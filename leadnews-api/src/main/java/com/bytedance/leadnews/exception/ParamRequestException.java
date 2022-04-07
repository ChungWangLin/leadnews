package com.bytedance.leadnews.exception;

public class ParamRequestException extends RuntimeException{
    private  String message;

    public ParamRequestException() {}

    public ParamRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
