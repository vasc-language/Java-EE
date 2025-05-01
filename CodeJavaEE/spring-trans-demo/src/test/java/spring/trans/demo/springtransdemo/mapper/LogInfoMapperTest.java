package spring.trans.demo.springtransdemo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 18:31
 */
@SpringBootTest
class LogInfoMapperTest {
    @Autowired
    private LogInfoMapper infoMapper;

    @Test
    void insertLog() {
        Integer i = infoMapper.insertLog("李四", "用户登录");
        System.out.println("返回条数：" + i);
    }
}