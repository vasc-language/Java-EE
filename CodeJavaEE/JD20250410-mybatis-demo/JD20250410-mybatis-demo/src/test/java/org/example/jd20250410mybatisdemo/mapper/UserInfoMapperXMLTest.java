package org.example.jd20250410mybatisdemo.mapper;

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
 * Date: 2025-04-13
 * Time: 18:17
 */
@SpringBootTest
class UserInfoMapperXMLTest {
    @Autowired
    private UserInfoMapperXML userInfoMapperXML;

    // 查询
    @Test
    void selectAll() {
        userInfoMapperXML.selectAll().stream().forEach(x -> System.out.println(x));
    }

    @Test
    void selectAll2() {
        userInfoMapperXML.selectAll2().stream().forEach(x -> System.out.println(x));
    }

    @Test
    void selectAll3() {
        userInfoMapperXML.selectAll3().stream().forEach(x -> System.out.println(x));
    }

    /**
     * 测试根据用户名和密码查询用户
     */
    @Test
    void selectByNameAndPassword() {
        // 使用数据库中已存在的用户名和密码
        String username = "zhangsan";
        String password = "zhangsan";
        
        // 调用mapper方法并获取结果
        userInfoMapperXML.selectByNameAndPassword(username, password)
                .stream()
                .forEach(user -> System.out.println(user));
                
        // 可选：添加断言验证结果
        assertFalse(userInfoMapperXML.selectByNameAndPassword(username, password).isEmpty(),
                "应该能找到至少一个匹配的用户");
    }

    @Test
    void insertUser() {
        String username = "王安福";
        String password = "王安福";
        Integer age = 91;
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setAge(age);

        Integer result = userInfoMapperXML.insertUser(userInfo);
        System.out.println("插入成功, 插入条数为:" + result);
    }

    @Test
    void insertUser2() {
        String username = "赵安语";
        String password = "赵安语";
        Integer age = 41;
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        userInfo.setAge(age);
        Integer result = userInfoMapperXML.insertUser2(userInfo);
        System.out.println("插入成功! 插入条数为:" + result + " 自增主键值为:" + userInfo.getId());
    }

    /**
     * 测试更新用户信息
     */
    @Test
    void updateUser() {
        // 准备新的用户名和密码以及年龄
        String newUsername = "张衡玉";
        String newPassword = "张衡玉";
        Integer age = 30;
        Integer id = 16;
        
        // 调用更新方法
        Integer result = userInfoMapperXML.updateUser(newUsername, newPassword, age, id);
        
        // 输出更新结果
        System.out.println("更新成功! 更新条数为:" + result);
        
        // 可选：验证更新是否成功 - 通过查询所有用户并筛选id=19的记录
        List<UserInfo> allUsers = userInfoMapperXML.selectAll();
        UserInfo updatedUser = allUsers.stream()
                .filter(user -> user.getId() != null && user.getId() == 19)
                .findFirst()
                .orElse(null);
                
        if (updatedUser != null) {
            System.out.println("更新后的用户信息: " + updatedUser);
            // 添加断言验证
            assertEquals(newUsername, updatedUser.getUsername());
            assertEquals(newPassword, updatedUser.getPassword());
        } else {
            System.out.println("未找到id=19的用户记录");
        }
    }

    @Test
    void deleteUser() {
        Integer id = 17;
        Integer result = userInfoMapperXML.deleteUser(id);
        System.out.println("删除成功! 更新条数为:" + result);
    }
}