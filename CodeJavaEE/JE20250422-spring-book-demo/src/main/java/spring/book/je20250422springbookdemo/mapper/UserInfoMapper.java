package spring.book.je20250422springbookdemo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import spring.book.je20250422springbookdemo.model.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 21:04
 */
@Mapper
public interface UserInfoMapper {
    @Select("SELECT * FROM `user_info` WHERE user_name = #{username}")
    UserInfo queryUserInfoByName(String username);
}
