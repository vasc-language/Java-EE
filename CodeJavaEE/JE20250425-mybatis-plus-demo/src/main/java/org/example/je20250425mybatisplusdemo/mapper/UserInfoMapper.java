package org.example.je20250425mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.je20250425mybatisplusdemo.model.UserInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 22:54
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    // 也可以自行输入 SQL 语句
    // selectById
    @Select("SELECT * FROM `user_info` WHERE id = #{id}")
    UserInfo selectById(Integer id);

    // 使用 XML 实现 selectById2()
    UserInfo selectById2(Integer id);


    // 将前面部分包装起来
    @Select("SELECT id, username, `password`, age, gender, phone, delete_flag FROM user_info ${ew.customSqlSegment}")
    List<UserInfo> selectUserInfoByCondition(@Param(Constants.WRAPPER) Wrapper<UserInfo> queryWrapper);

    //
    @Update("UPDATE user_info SET age = age + #{addAge} ${ew.customSqlSegment}")
    Integer updateById(Integer addAge, @Param(Constants.WRAPPER) Wrapper<UserInfo> queryWrapper);
}
