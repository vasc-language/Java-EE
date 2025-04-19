package org.example.jd20250417springbookdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.example.jd20250417springbookdemo.model.PageRequest;

import java.util.List;

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


    /** 处理分页情况
     * offset：起始页码
     * pageSize：一个页面有多少页码
     * @param pageRequest 页面
     * @return
     */
    @Select("SELECT * FROM book_info WHERE `status` <> 0 LIMIT #{offset}, #{pageSize}")
    List<BookInfo> selectBooksByPage(PageRequest pageRequest);

    // 现存多少页码（需要总的页码进行分页）
    @Select("SELECT COUNT(1) FROM book_info WHERE `status` <> 0")
    Integer count();

    // 更新修改图书
    Integer updateBook(BookInfo bookInfo);

    // 批量删除图书
    Integer batchDelete(List<Integer> ids);

}
