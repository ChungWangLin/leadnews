package com.bytedance.leadnews.exception;

import com.bytedance.leadnews.common.pojo.dto.FailedBody;
import com.bytedance.leadnews.util.RequestContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求体参数异常处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FailedBody methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        String requestURI = RequestContextUtil.getRequest().getRequestURI();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return FailedBody.failed(HttpStatus.BAD_REQUEST.value(),collect.get(0),requestURI);
    }

    /**
     * 自定义参数校验异常处理
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParamRequestException.class)
    public FailedBody paramRequestExceptionHandler(ParamRequestException e) {
        String requestURI = RequestContextUtil.getRequest().getRequestURI();
        return FailedBody.failed(HttpStatus.BAD_REQUEST.value(), e.getMessage(),requestURI);
    }

    /**
     * 资源已存在异常处理
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ConflictException.class)
    public FailedBody conflictExceptionHandler(ConflictException e) {
        String requestURI = RequestContextUtil.getRequest().getRequestURI();
        return FailedBody.failed(HttpStatus.CONFLICT.value(), e.getMessage(), requestURI);
    }

}
