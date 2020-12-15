package com.hch.dao;

import com.hch.pojo.po.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author hch
 * @since 2020/8/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void testListUsers() {
        System.out.println(userDao.listUsers());
    }

    @Test
    public void testInsertUser() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            UserDO userDO = new UserDO();
            userDO.setName(generateName(random));
            userDO.setPhoneNumber(generatePhoneNum(random));
            userDO.setUuid(UUID.randomUUID().toString());
            userDao.insertUser(userDO);
        }

    }

    private static String generatePhoneNum(Random random) {
        StringBuilder stringBuilder = new StringBuilder("1");
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

    private static String generateName(Random random) {
        StringBuilder stringBuilder = new StringBuilder();
        int limit = 1 + random.nextInt(30);
        for (int i = 0; i < limit; i++) {
            stringBuilder.append((char) (97 + random.nextInt(26)));
        }
        return stringBuilder.toString();
    }

}