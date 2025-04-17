package org.example.jd20250417springbootdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.jd20250417springbootdemo.model.MessageInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 18:55
 */
@Mapper
public interface MessageMapper {
    /**
     * 查询所有留言信息
     * @return
     */
    @Select("select * from message_info where delete_flag = 0")
    List<MessageInfo> queryAll();

    /**
     * 将前端输入的数据，存储到数据库中
     * @param messageInfo
     * @return
     */
    @Insert("insert into message_info (`from`, `to`, message) values (#{from}, #{to}, #{message})")
    Integer insertMessage(MessageInfo messageInfo);


}
