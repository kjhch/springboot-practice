package com.hch.dao;

import com.hch.pojo.po.UserDO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author hch
 * @since 2020/8/16
 */
@Mapper
public interface UserDao {
    @Select("SELECT * FROM tbl_user")
    List<UserDO> listUsers();

    void insertUser(UserDO userDO);
}
