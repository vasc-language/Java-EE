package org.example.jd20250410mybatisdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.jd20250410mybatisdemo.model.UserInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-13
 * Time: 18:12
 */
@Mapper
public interface UserInfoMapperXML {
    /**
     * 注释和 XML是可以共存的
     * XML步骤
     * 1. 添加依赖：mybatis-spring-boot-starter、mysql-connector-j
     * 2. 配置数据库信息
     * 3. 定义接口
     * 4. 使用 XML 实现接口
     * @return
     */
    // 传递一个参数
    List<UserInfo> selectAll();

    List<UserInfo> selectAll2();

    List<UserInfo> selectAll3();

    // 传递多个参数
    List<UserInfo> selectByNameAndPassword(
        @Param("username") String username,
        @Param("password") String password
    );

    // 插入一条数据
    Integer insertUser(UserInfo userInfo);

    // 插入一条数据, 返回自增的主键 + 绑定参数
    Integer insertUser2(@Param("userInfo") UserInfo userInfo);


    // 更新一条数据
    Integer updateUser(String username, String password, Integer age, Integer id);

    // 删除一条数据
    Integer deleteUser(Integer id);

    // 测试动态SQL
    // 添加 <if> 和 <trim>
    Integer insertUser3(UserInfo userInfo);

    // 添加 <foreach> 批量插入
    Integer batchInsertUsers(@Param("userInfos") List<UserInfo> userInfos);
    // 添加 <where>
    List<UserInfo> selectByCondition(UserInfo userInfo);
    // 添加 <set>
    Integer updateByConfition(UserInfo userInfo);

    // 添加 <forEach> 批量删除
    Integer batchDelete(List<Integer> ids); //

    // 添加 <sql>
    List<UserInfo> selectAllBySQL();

}
