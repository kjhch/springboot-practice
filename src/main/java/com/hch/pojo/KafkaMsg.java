package com.hch.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author hch
 * @since 2021/1/11
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class KafkaMsg {
    @NotBlank(message = "name cannot be blank")
    private String name;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Boolean male;
}
