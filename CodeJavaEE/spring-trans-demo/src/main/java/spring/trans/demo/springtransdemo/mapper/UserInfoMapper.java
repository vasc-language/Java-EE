package spring.trans.demo.springtransdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 17:45
 */
@Mapper
public interface UserInfoMapper {

    @Insert("INSERT INTO user_info (user_name, `password`) VALUES (#{userName}, #{password})")
    Integer insert(String userName, String password);

}
