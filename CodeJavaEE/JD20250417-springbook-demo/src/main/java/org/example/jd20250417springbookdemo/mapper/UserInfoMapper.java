package org.example.jd20250417springbookdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.jd20250417springbookdemo.model.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 21:26
 */
@Mapper
public interface UserInfoMapper {
    @Select("SELECT * FROM `user_info` WHERE user_name = #{username}")
    UserInfo queryUserInfoByName(String username);
}
