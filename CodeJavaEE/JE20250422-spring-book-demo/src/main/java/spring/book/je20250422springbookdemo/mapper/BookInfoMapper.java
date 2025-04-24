package spring.book.je20250422springbookdemo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import spring.book.je20250422springbookdemo.model.BookInfo;
import spring.book.je20250422springbookdemo.model.PageRequest;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 7:05
 */
@Mapper
public interface BookInfoMapper {
    // 插入图书操作
    @Insert("INSERT INTO book_info (book_name, author, count, price, publish, status)" +
            " VALUES" +
            " (#{bookName}, #{author}, #{count}, #{price}, #{publish}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id") // 主键ID自增
    Integer addBooks(BookInfo bookInfo);

    // 直接通过 status 是否等于 0 来逻辑删除 数据条
    @Select("SELECT COUNT(1) FROM book_info WHERE status <> 0")
    Integer count();

    /**
     * 分页操作
     * @param pageRequest 将页面所有的属性封装成一个对象
     * @return
     */
    @Select("SELECT * FROM `book_info` WHERE status <> 0 LIMIT #{offset}, #{pageSize}")
    List<BookInfo> selectBooksByPage(PageRequest pageRequest);

    // 查询更新后的图书信息
    @Select("SELECT * FROM book_info WHERE `status` != 0 AND id = #{bookId}")
    BookInfo QueryBooksBuId(Integer bookId);

    // 更新图书信息
    Integer updateBooks(BookInfo bookInfo);

    // 删除图书信息，可以直接调用 更新的操作，只是逻辑上的删除
    // 只需要修改 status 的值即可

    // 批量删除图书
    Integer batchDelete(List<Integer> ids);
}
