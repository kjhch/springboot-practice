package com.hch.aop;

import com.hch.api.ErrorEnum;
import com.hch.api.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAspect {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonResponse<String> handleIllegalArgsException(IllegalArgumentException e) {
        CommonResponse<String> response = new CommonResponse<>(ErrorEnum.CLIENT_ERROR);
        response.setData(e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse<String> handleException(Exception e) {
        CommonResponse<String> response = new CommonResponse<>(ErrorEnum.UNKNOWN_ERROR);
        response.setData(e.getMessage());
        return response;
    }
}
