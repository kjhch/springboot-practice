package com.hch.pojo.response;

import com.hch.pojo.ErrorEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@NoArgsConstructor
@XmlRootElement(name = "resp") // 使用Accept:application/xml时，response会自动转为xml格式
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getLocalMessage();
    }
}
