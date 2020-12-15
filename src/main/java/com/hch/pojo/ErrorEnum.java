package com.hch.pojo;

import lombok.Getter;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Getter
public enum ErrorEnum {
    // error code enum  参考阿里java手册
    SUCCESS("00000", "success", "成功"),
    CLIENT_ERROR("A0001", "client error", "客户端错误"),
    UNKNOWN_ERROR("B0001", "server unknown error", "服务器未知错误");

    private String code;
    private String enMessage;
    private String zhMessage;

    ErrorEnum(String code, String enMessage, String zhMessage) {
        this.code = code;
        this.enMessage = enMessage;
        this.zhMessage = zhMessage;
    }

    public String getLocalizedMessage() {
        Locale requestLocale = LocaleContextHolder.getLocale();
        // System.out.println(requestLocale);
        if (requestLocale.equals(Locale.CHINA)) {
            return zhMessage;
        }
        return enMessage;
    }

}
