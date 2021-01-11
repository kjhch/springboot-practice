package com.hch.pojo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hch.pojo.ErrorEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
// @XmlRootElement(name = "resp") // 当请求头包含Accept:application/xml时，response会自动转为xml格式
public class CommonResponse<T> {
    private String code;
    private String message;
    private T data;

    public CommonResponse() {
        this.code = ErrorEnum.SUCCESS.getCode();
        this.message = ErrorEnum.SUCCESS.getLocalizedMessage();
    }

    public CommonResponse(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getLocalizedMessage();
    }
}
