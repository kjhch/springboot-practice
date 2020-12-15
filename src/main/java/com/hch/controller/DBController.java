package com.hch.controller;

import com.hch.pojo.ErrorEnum;
import com.hch.pojo.dto.UserDTO;
import com.hch.pojo.po.UserDO;
import com.hch.pojo.response.CommonResponse;
import com.hch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hch
 * @since 2020/8/16
 */
@RestController
public class DBController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public CommonResponse<List<UserDO>> listUsers() {
        return new CommonResponse<List<UserDO>>(ErrorEnum.SUCCESS).setData(userService.listUsers());
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse insertUser(@RequestBody(required = false) UserDTO userDTO){
        userService.insertUser(userDTO);
        return new CommonResponse<>(ErrorEnum.SUCCESS);
    }
}
