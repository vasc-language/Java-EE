package org.example.jd20250417springbookdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.example.jd20250417springbookdemo.model.BookInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 22:36
 */
@Mapper
public interface BookInfoMapper {
    // 插入图书信息
    @Insert("INSERT INTO book_info " +
            "(id, book_name, author, count, price, publish, `status`)" +
            "VALUES " +
            " (#{id}, #{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status})")
    Integer addBook(BookInfo bookInfo);

}
