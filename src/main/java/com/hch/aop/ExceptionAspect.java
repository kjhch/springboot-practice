package com.hch.aop;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

@ControllerAdvice
@Slf4j
public class ExceptionAspect {
    @ExceptionHandler({IllegalArgumentException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public CommonResponse<String> handleIllegalArgsException(Exception e) {
        log.debug("bad request", e);
        CommonResponse<String> response = new CommonResponse<>(ErrorEnum.CLIENT_ERROR);
        response.setData(e.getMessage());
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public CommonResponse<String> handleException(Exception e, Locale locale) {
        // System.out.println(locale);        // 可通过Accept-Language传入语言，得到locale信息从而进行国际化
        CommonResponse<String> response = new CommonResponse<>(ErrorEnum.UNKNOWN_ERROR);
        response.setData(e.getMessage());
        return response;
    }
}
