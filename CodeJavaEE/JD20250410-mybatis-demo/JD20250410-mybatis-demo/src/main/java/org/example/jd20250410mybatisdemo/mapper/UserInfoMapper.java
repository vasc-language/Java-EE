package org.example.jd20250410mybatisdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.jd20250410mybatisdemo.model.UserInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 对数据库进行所有的操作
 * User: 姚东名
 * Date: 2025-04-10
 * Time: 7:51
 */

 /**
  * 接口的实现方式有两种：
  1. 注释来实现
  2. 通过 XML 方式实现
  */
@Mapper
public interface UserInfoMapper {
    // 编写步骤：Mapper(与数据库进行联系操作 mybatis 框架进行负责) 
    // -> Service(进行业务逻辑处理) 
    // -> Controller(与客户端进行交互)
    // 增、删、改、查
    
    // 1. 传递一个参数
    @Select("SELECT * FROM user_info")
    List<UserInfo> selectAll();

    // #{id} (标准使用)
    /**
     * 1. 采用预编译参数方式
     * 2. 会被转化为成?占位符，然后通过参数绑定传值
     * 3. 自动处理类型：根据参数类型自动添加引号
     * 4. 安全性高：可以防止SQL注入攻击
     * @param id
     * @return
     */
    @Select("SELECT * FROM user_info WHERE id = #{id};")
    UserInfo selectAllById(Integer id);

    /**
     * '${id}'
     * 1. 采用直接文本替换方式
     * 2. 将参数直接替换到SQL语句中
     * 3. 不处理参数类型：需要手动添加引号（如代码中的'${id}'）
     * 4. 安全性低：容易导致SQL注入风险
     * @param id
     * @return
     */
    @Select("SELECT * FROM user_info WHERE id = '${id}';")
    UserInfo selectAllById2(Integer id);

    // 2. 传递多个参数 -> 使用注释 @Param 来进行参数的绑定，避免 Spring 容器在进行对象赋值发生混乱

     // 这个使用 #{}
    @Select("SELECT * FROM user_info WHERE username = #{userName} AND `password` = #{password};")
    List<UserInfo> selectByNameAndPassword(
        @Param("userName") String username, 
        @Param("password") String password);
        
    // 这个使用 ${}
    @Select("SELECT * FROM user_info WHERE username = '${userName}' AND `password` = '${password}';")
    List<UserInfo> selectByNameAndPassword2(
        @Param("password") String password,
        @Param("userName") String username
    );
    
    // 3. 将要插入的数据封装成一个对象
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO user_info (username, `password`, age) VALUES (#{username}, #{password}, #{age});")
    Integer insertUser(UserInfo userInfo);


    /**
     * 3.2 绑定参数
     * 介绍注释: @Option(userGeneratedKeys = true, keyProperty)
     * 基本作用：当你向数据库中插入一条新纪录时，通常主键列（id）是由数据库自动生成的（自增ID），这个注释能够在插入数据操作完成后将自增的Key返回来
     * 1.  
     * - 指示 mybatis 在执行完 Insert 操作后尝试获取数据库自增的 Key
     * - userGeneratedKeys = true 就是告诉 JDBC 驱动程序需要返回自动生成的Key
     * 2. keyProperty = id
     * - 指定获取到的自增Key 应该设置在哪个属性中
     * - 在这个例子中，自动生成的Key，被传入了 userInfo 对象的 id 属性中
     * @param userInfo
     * @return
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO user_info (username, `password`, age) " +
           "VALUES (#{userInfo.username}, #{userInfo.password}, #{userInfo.age})")
    Integer insertUser2(@Param("userInfo") UserInfo userInfo);
    
    // 这是错误示范 -> SQL 字符串拼接中格式问题
    // 实际生成的SQL语句是：INSERT INTOuser_info 两个词是在一起的
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO"
    + "user_info (username, `password`, age) "
    + "VALUES (#{userInfo.username}, #{userInfo.password}, #{userInfo.age});")
    Integer insertUser3(@Param("userInfo") UserInfo userInfo);

    // 数据库查询时，存在当字段名和属性名对不上（例如delete_Flag 和 deleteFlag 不相同）
    // 1. 

    // 测试#{} 和 ${}
    @Select("SELECT * FROM `user_info` WHERE id = #{id}")
    UserInfo selectAllById5(Integer id);

    @Select("SELECT * FROM `user_info` WHERE id = ${id}")
    UserInfo selectAllById6(Integer id);

    @Select("SELECT * FROM user_info WHERE username = #{username} AND `password` = #{password}")
    List<UserInfo> selectByNameAndPassword4(
            @Param("username") String userName,
            @Param("password") String password
    );

     // @Select("SELECT * FROM user_info WHERE username = '${username}' AND `password` = '${password}'")
     // @Select("SELECT * FROM `user_info` where username='${username}' and `password` = '${password}' ")
     @Select("SELECT * FROM `user_info` where username='${username}' and `password` = '${password}' ")
     List<UserInfo> selectByNameAndPassword5(
             @Param("username") String userName,
             @Param("password") String password
     );

     // 但是 ${} 有其的用途
     // Preparing: SELECT * FROM user_info ORDER BY id DESC
     /**
      * ${} 适用场景
      * 1. 动态表名：FROM ${tableName}
      * 2. 动态列名：ORDER BY ${columnName}
      * 3. 动态SQL关键字：ORDER BY id ${orderDirection}（ASC/DESC）
      * 4. 特殊SQL结构：需要在SQL结构中插入不同关键字的场景
      * @param order
      * @return
      */
     @Select("SELECT * FROM user_info ORDER BY id ${order}")
     List<UserInfo> selectUserByOrder(String order);

     @Select("SELECT * FROM user_info ORDER BY id #{order}")
    List<UserInfo> selectUserByOrder2(String order);

     // 模糊查询
     // ${}
     @Select("SELECT * FROM user_info WHERE username LIKE CONCAT('%','${username}','%')")
     List<UserInfo> selectUserInfoByLike(@Param("username") String userName);

     // #{}
     /**
      * 模糊查询使用 concat 函数配合#{}：既保证功能又保证安全
      * @param userName
      * @return
      */
     @Select("SELECT * FROM user_info WHERE username LIKE CONCAT('%',#{username},'%')")
     List<UserInfo> selectUserInfoByLike2(@Param("username") String userName);
}
