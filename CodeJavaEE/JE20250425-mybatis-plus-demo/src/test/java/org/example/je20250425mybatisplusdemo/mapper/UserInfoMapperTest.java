package org.example.je20250425mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.example.je20250425mybatisplusdemo.model.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void insert() {
        UserInfo userInfo = new UserInfo();

        userInfo.setUsername("username3");
        userInfo.setPassword("password3");
        userInfo.setAge(19);
        userInfo.setPhone("1919191919");
        userInfo.setGender(0);

        // “serializable”的常见意思是：可序列化的；可连载的；可排成系列的 。
        userInfoMapper.insert(userInfo);
    }

    @Test
    void delete() {
        userInfoMapper.deleteById(3);
    }

    @Test
    void update() {
        UserInfo userInfo = new UserInfo();

        userInfo.setId(7);
        userInfo.setUsername("admin11");
        userInfo.setPassword("admin11");
        userInfo.setAge(19);
        userInfo.setPhone("191919191919");
        userInfo.setGender(0);

        userInfoMapper.updateById(userInfo);
    }

    @Test
    void select() {
        System.out.println(userInfoMapper.selectById(2));
    }

    /**
     * 1. 复合查询
     * QueryWrapper 包装器：专门用于构造查询条件，支持基本的等于、不等于、大于、小于等各种逻辑操作
     * 它允许你以链式调用的方式添加多个查询条件，并且可以组合使用 and 和 or 逻辑
     * eq（equals）
     * gt（Greater than）
     * ge（Greater than or equal to）
     * lt（Less than）
     * le（Less than or equal to）
     */

    @Test
    void selectByCondition() {
        // SELECT id, username, `password`, age, gender, phone delete_flag
        // FROM user_info
        // WHERE age = 19 AND username LIKE '%username%'

        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id, username, `password`, age, gender, phone delete_flag")
                .eq("age", 19)
                .like("username", "username");

        userInfoMapper.selectList(queryWrapper).forEach(System.out::println);
    }


    // 强烈推荐，这是 MyBatis-Plus 提倡的方式，更安全、更易维护。
    @Test
    void selectByCondition2() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().select(UserInfo::getId, UserInfo::getUsername, UserInfo::getPassword,
                UserInfo::getAge, UserInfo::getGender, UserInfo::getPhone, UserInfo::getDeleteFlag)
                .eq(UserInfo::getAge, 19)
                .like(UserInfo::getUsername, "username");
        userInfoMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    /**
     * 2. 更新操作
     * UpdateWrapper 包装器：用于构造更新条件，可以在更新数据时指定条件，与 QueryWrapper 类似，
     * 它也支持链式调用和逻辑组合。使用 UpdateWrapper 可以在不创建实体类对象情况下，直接设置更新
     * 字段和条件
     */

    // QueryWrapper 本身没有设置 set 的方法，但是 update(entity, wrapper) 这个方法会利用 entity
    // 来生成 set 句子

    /**
     * QueryWrapper 和 UpdateWrapper 的区别
     * - update(entity, queryWrapper) 适合于你已经有一个包含更新值的实体类，并且想根据 QueryWrapper
     * 定义的条件来更新的情况。set 子句是由实体类内容驱动
     * - update(updateWrapper) 它允许你在不创建实体对象的情况下，直接、精准地指定要更新的列和值（通过
     *   .set() 或 .setSql()），以及更新条件。set 在子句是由 UpdateWrapper 自身定义
     */
    @Test
    void updateByCondition() {
        UserInfo userInfo = new UserInfo();
        userInfo.setDeleteFlag(1);

        // UPDATE user_info SET set_deleteFlag = 1 WHERE age < 20
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("age", 20);

        userInfoMapper.update(userInfo, queryWrapper);
    }

    @Test
    void updateByCondition2() {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("delete_flag", 0)
                .lt("age", 20);
        userInfoMapper.update(updateWrapper);
    }

    @Test
    void updateByCondition3() {
        // UPDATE user_info SET delete_flag = 1, age = 10 WHERE id IN (4, 5, 6)
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        // 演示了如何通过链式调用.set()方法来更新多个字段
        updateWrapper.set("delete_flag", 1).set("age", 10)
                .in("id", List.of(4, 5, 6));
        userInfoMapper.update(updateWrapper);
    }

    @Test
    void updateByCondition4() {
        // UPDATE user_info SET age = age + 20 WHERE id IN (1, 2, 4)
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        // 演示了如何使用 .setSql() 方法来直接嵌入 SQL 片段到 set 子句中
        updateWrapper.setSql("age = age + 20")
                .in("id", List.of(1, 2, 4));
        userInfoMapper.update(updateWrapper);
    }

    @Test
    void updateByCondition5() {
        // UPDATE user_info SET delete_flag = 1 WHERE age < 20
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        // 利用 lambda 表达式风格应用于 UpdateWrapper
        updateWrapper.lambda().set(UserInfo::getDeleteFlag, 1)
                .lt(UserInfo::getAge, 20);
        userInfoMapper.update(updateWrapper);
    }

    // 删除操作
    @Test
    void deleteByCondition() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        // DELETE FROM user_info WHERE age = 20
        queryWrapper.eq("age", 20);
        userInfoMapper.delete(queryWrapper);
    }

    // 自行实现 SQl 语句
    // 查询操作
    @Test
    void selectById2() {
        System.out.println(userInfoMapper.selectById(2));
    }

    @Test
    void selectById3() {
        UserInfo userInfo = userInfoMapper.selectById(2);
        System.out.println(userInfo);
    }

    /**
     * 这两个方法是如何利用 MyBatis-plus 的Wrapper 来拼接 SQL 语句
     * 核心在于结合了手写 SQL（通过 @Select 和 @Update 注解）和 MyBatis-plus 的动态构造器（Wrapper）
     * 1. @Param(Constants.WRAPPER) Wrapper<UserInfo> queryWrapper
     *    - @Param 是 MyBatis 的注解，用于方法参数绑定，以便在 SQL 语句中引用
     *    - Constants.WRAPPER 是 Mybatis-plus 提供的一个常量，它的值通常就是字符串 "ew"（Entity Wrapper）
     *      所以 @Param(Constants.WRAPPER) 等价于 @Param("ew")
     *    - 这整句的作用：将方法参数 queryWrapper（它是一个 Wrapper 对象）在 SQL 语句中命名为 ew
     * 2. ${ew.customSqlSegment}
     *    - 这是 MyBatis-plus 的一个特性。当你在 @Select, @Update, @Delete, @Insert 注解的 SQL 语句或 XML
     *      映射文件中使用 ${ew.customSqlSegment} 时，MyBatis-plus 会自动将你传入的、名为 ew 的 Wrapper 对象
     *      构造的 SQl 片段（通常是 WHERE 子句以及后面的部分，如 ORDER BY, GROUP BY 等）原样拼接到这个位置
     *    - 注意: 这里用的是 ${...} 而不是 #{...}。${...} 表示字符串替换，MyBatis-Plus 会直接把 Wrapper 生成
     *      的 SQL 字符串（例如 "WHERE age = 19 AND username LIKE '%user%'"）插入到这里。而 #{...} 是预编译
     *      参数占位符，用于防止 SQL 注入。
     */
    @Test
    void selectUserInfoByCondition() {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        // SELECT id, username, `password`, age, gender, phone, delete_flag FROM user_info WHERE age <= 40
        queryWrapper.le("age", 40);
        userInfoMapper.selectUserInfoByCondition(queryWrapper).forEach(System.out::println);
    }

    // 更新操作
    @Test
    void updateById() {
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        // UPDATE user_info SET age = age + 10 WHERE id IN (4, 7, 8)
        updateWrapper.in("id", List.of(4, 7, 8));
        userInfoMapper.updateById(10, updateWrapper);
    }

}