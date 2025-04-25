package org.example.je20250425mybatisplusdemo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 22:57
 */
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void selectById() {
        System.out.println(userInfoMapper.selectById(2));
    }

    @Test
    void selectById2() {
        System.out.println(userInfoMapper.selectById(2));
    }
}