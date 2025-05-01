package spring.trans.demo.springtransdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 18:28
 */

@Mapper
public interface LogInfoMapper {
    @Insert("INSERT INTO log_info (user_name, op) VALUES (#{userName}, #{op})")
    Integer insertLog(String userName, String op);
}
