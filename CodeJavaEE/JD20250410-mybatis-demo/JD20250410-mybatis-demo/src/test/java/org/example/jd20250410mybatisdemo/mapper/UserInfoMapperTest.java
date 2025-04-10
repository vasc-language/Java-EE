package org.example.jd20250410mybatisdemo.mapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import org.example.jd20250410mybatisdemo.model.UserInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-10
 * Time: 8:05
 */
@SpringBootTest
class UserInfoMapperTest {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setUp() {
        System.out.println("before~");
    }

    @AfterEach
    void tearDown() {
        System.out.println("after~");
    }

    @Test
    void selectAll() {
        System.out.println(userInfoMapper.selectAll());
    }

    // 测试#{id}
    @Test
    void selectAllById() {
        System.out.println(userInfoMapper.selectAllById(1));
    }
    // 测试 '${id}' 是否和上面的 #{id} 效果相同
    @Test
    void selectAllById2() {
        System.out.println(userInfoMapper.selectAllById2(2));
    }

    // 也就是说，@Test 测试代码不应该有参数
    @Test
    void selectByNameAndPassword() {
        String userName = "zhangsan";
        String password = "zhangsan";
        List<UserInfo> userInfos = userInfoMapper.selectByNameAndPassword(userName, password);
        userInfos.forEach(x -> System.out.println(x));
    }

    @Test
    void selectByNameAndPassword2() {
        // String userName = "lisi";
        // String password = "lisi";
        // List<UserInfo> userInfos = userInfoMapper.selectByNameAndPassword(userName, password);
        // userInfos.forEach(System.out::println);

        userInfoMapper.selectByNameAndPassword2("zhangsan", "zhangsan")
                .stream().forEach(x -> System.out.println(x));
    }

    @Test
    void selectByNameAndPassword3() {
        userInfoMapper.selectByNameAndPassword2("' or 1='1", "zhangsan")
                .stream().forEach(x-> System.out.println(x));
    }

    @Test
    void insertUser() {
        // 1. 准备数据
        String userName = "陈长生";
        String password = "陈长生";
        Integer age = 19;
        
        // 2. 创建UserInfo对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userName);
        userInfo.setPassword(password);
        userInfo.setAge(age);
        
        // 3. 调用mapper方法插入数据
        Integer result = userInfoMapper.insertUser(userInfo);
        
        // 4. 验证结果
        assertEquals(1, result); // 验证影响的行数为1
        assertNotNull(userInfo.getId()); // 验证已生成ID
        System.out.println("插入用户成功，生成的ID为: " + userInfo.getId());
        
        // 5. 可选：验证插入的记录是否可以查询到
        UserInfo insertedUser = userInfoMapper.selectAllById(userInfo.getId());
        assertNotNull(insertedUser);
        assertEquals(userName, insertedUser.getUsername());
        assertEquals(password, insertedUser.getPassword());
        assertEquals(age, insertedUser.getAge());
    }

    @Test
    void insertUser2() {
        // 1. 准备数据
        String username = "陆鸣玉";
        String password = "陆鸣玉";
        Integer age = 21;
        // 2. 创建 userInfo 对象
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setAge(age);
        // 3. 调用 UserInfoMapper 的 insertUser2
        Integer result = userInfoMapper.insertUser3(userInfo);
        // 4. 验证结果
        assertEquals(1, result); // 影响行数
        assertNotNull(userInfo.getId()); // 验证已生成 ID
        System.out.println("插入用户成功，生成ID为：" + userInfo.getId());
    }
}