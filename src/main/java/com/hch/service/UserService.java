package com.hch.service;

import com.hch.dao.UserDao;
import com.hch.pojo.dto.UserDTO;
import com.hch.pojo.po.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hch
 * @since 2020/8/16
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<UserDO> listUsers() {
        return userDao.listUsers();
    }

    public void insertUser(UserDTO userDTO){
        UserDO userDO = new UserDO();

        userDao.insertUser(userDO);
    }

}
