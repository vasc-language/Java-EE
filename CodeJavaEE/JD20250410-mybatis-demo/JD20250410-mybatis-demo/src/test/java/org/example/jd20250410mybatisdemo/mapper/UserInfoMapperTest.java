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
                .stream().forEach(x -> System.out.println(x));
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

    @Test
    void selectAllById5() {
        // SELECT * FROM `user_info` WHERE id = ?
        System.out.println(userInfoMapper.selectAllById5(10));
    }

    @Test
    void selectAllById6() {
        // SELECT * FROM `user_info` WHERE id = 10
        System.out.println(userInfoMapper.selectAllById6(10));
    }

    @Test
    void selectByNameAndPassword4() {
        String username = "' or = '1";
        String password = "' or 1='1";

        // SELECT * FROM user_info WHERE username = ? AND `password` = ?
        // Parameters: ' or = '1(String), ' or 1='1(String)' or = '1(String), ' or 1='1(String)
        userInfoMapper.selectByNameAndPassword4(username, password)
                .stream()
                .forEach(userInfo -> System.out.println(userInfo));
    }

    @Test
    void selectByNameAndPassword5() {
        String username = "' or 1='1";
        // String username = "zhangsan";
        String password = "' or 1='1";

        // SELECT * FROM `user_info` where username='' or 1='1' and `password` = '' or 1='1'
        userInfoMapper.selectByNameAndPassword5(username, password)
                .stream()
                .forEach(userInfo -> System.out.println(userInfo));
    }

    /**
     * 测试使用${} 动态指定排序方式的场景
     * 这是${} 的正确使用场景之一：动态指定SQL语句结构（如排序方式）
     */
    @Test
    void selectUserByOrder() {
        System.out.println("===== 测试升序排序 =====");
        // 升序排序
        List<UserInfo> ascUsers = userInfoMapper.selectUserByOrder("ASC");
        System.out.println("ASC排序结果:");
        ascUsers.forEach(user -> System.out.println("ID: " + user.getId() + ", 用户名: " + user.getUsername()));

        System.out.println("\n===== 测试降序排序 =====");
        // 降序排序
        List<UserInfo> descUsers = userInfoMapper.selectUserByOrder("DESC");
        System.out.println("DESC排序结果:");
        descUsers.forEach(user -> System.out.println("ID: " + user.getId() + ", 用户名: " + user.getUsername()));

        // 断言测试：确保升序和降序结果顺序相反
        if (!ascUsers.isEmpty() && !descUsers.isEmpty()) {
            // 检查第一个元素
            Integer firstIdAsc = ascUsers.get(0).getId();
            Integer firstIdDesc = descUsers.get(0).getId();

            // 检查最后一个元素
            Integer lastIdAsc = ascUsers.get(ascUsers.size() - 1).getId();
            Integer lastIdDesc = descUsers.get(descUsers.size() - 1).getId();

            // 断言验证排序正确性
            System.out.println("\n===== 验证排序结果 =====");
            System.out.println("升序首个ID: " + firstIdAsc + ", 降序首个ID: " + firstIdDesc);
            System.out.println("升序末尾ID: " + lastIdAsc + ", 降序末尾ID: " + lastIdDesc);

            // 如果排序正确，升序的第一个应该是降序的最后一个，反之亦然
            if (ascUsers.size() > 1 && descUsers.size() > 1) {
                assertEquals(firstIdAsc, lastIdDesc, "升序的第一个元素应该等于降序的最后一个元素");
                assertEquals(lastIdAsc, firstIdDesc, "升序的最后一个元素应该等于降序的第一个元素");
            }
        }

        // 测试SQL注入风险 - 正常情况下不应该这样使用${} 参数
        System.out.println("\n===== 测试SQL注入风险 =====");
        try {
            // 尝试进行SQL注入
            List<UserInfo> injectionResult = userInfoMapper.selectUserByOrder("ASC; DROP TABLE user_info; --");
            System.out.println("SQL注入测试结果 (通常不会执行到这里):");
            injectionResult.forEach(user -> System.out.println(user));
        } catch (Exception e) {
            System.out.println("SQL注入测试触发异常: " + e.getMessage());
            // 异常是预期行为，因为不当使用${} 可能导致SQL注入
        }
    }
    // 错误的：使用 #{}
    // SELECT * FROM user_info ORDER BY id 'ASC' 
    // 正确的：使用 '${}'
    // SELECT * FROM user_info ORDER BY id ASC
    @Test
    void selectUserByOrder2() {
        System.out.println("===== 测试升序排序 =====");
        userInfoMapper.selectUserByOrder2("ASC")
                .stream()
                .forEach(userInfo -> System.out.println(userInfo));
    }

    // 测试 like 模糊查询
    @Test
    void selectUserInfoByLike() {
        userInfoMapper.selectUserInfoByLike("陆鸣玉")
                .stream()
                .forEach(userInfo -> System.out.println(userInfo));
    }

    @Test
    void selectUserInfoByLike2() {
        userInfoMapper.selectUserInfoByLike2("陆鸣玉")
                .stream()
                .forEach(userInfo -> System.out.println(userInfo));
    }


}