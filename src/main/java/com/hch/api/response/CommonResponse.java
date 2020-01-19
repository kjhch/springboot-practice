package com.hch.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hch.api.ErrorEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
public class CommonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResponse(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getLocalMessage();
    }
}
