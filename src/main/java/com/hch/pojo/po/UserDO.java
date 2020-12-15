package com.hch.pojo.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author hch
 * @since 2020/8/16
 */
@Data
public class UserDO {
    private Long id;
    private String uuid;
    private String name;
    private String phoneNumber;
    private LocalDateTime createdTime;
}
