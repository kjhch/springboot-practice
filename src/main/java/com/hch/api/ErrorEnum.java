package com.hch.api;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public enum ErrorEnum {
    // error code enum
    SUCCESS(10200, "success", "成功"),
    CLIENT_ERROR(10400, "client error", "客户端错误"),
    UNKNOWN_ERROR(10500, "server unknown error", "服务器未知错误");

    private int code;
    private String enMessage;
    private String zhMessage;

    ErrorEnum(int code, String enMessage, String zhMessage) {
        this.code = code;
        this.enMessage = enMessage;
        this.zhMessage = zhMessage;
    }

    public String getLocalMessage() {
        Locale requestLocale = LocaleContextHolder.getLocale();
        // System.out.println(requestLocale);
        if (requestLocale.equals(Locale.CHINA)) {
            return zhMessage;
        }
        return enMessage;
    }

}
