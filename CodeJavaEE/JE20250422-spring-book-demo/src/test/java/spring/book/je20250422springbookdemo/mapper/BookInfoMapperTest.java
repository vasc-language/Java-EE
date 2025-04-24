package spring.book.je20250422springbookdemo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.book.je20250422springbookdemo.model.BookInfo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 7:14
 */
@SpringBootTest
class BookInfoMapperTest {
    @Autowired
    private BookInfoMapper bookInfoMapper;
    @Test
    void addBooks() {
        BookInfo bookInfo = new BookInfo();
        // bookInfo.setId(100);
        bookInfo.setBookName("图书100");
        bookInfo.setAuthor("作者100");
        bookInfo.setCount(57);
        bookInfo.setPrice(new BigDecimal("66.77"));
        bookInfo.setPublish("出版社100");
        bookInfo.setStatus(1);
        
        // 执行插入操作
        Integer result = bookInfoMapper.addBooks(bookInfo);
        
        // 断言插入结果为1（表示成功插入1条记录）
        assertEquals(1, result);
    }

    @Test
    void batchDelete() {
        // 插入测试数据
        BookInfo book1 = new BookInfo();
        // book1.setId(101);
        book1.setBookName("图书101");
        book1.setAuthor("作者101");
        book1.setCount(10);
        book1.setPrice(new BigDecimal("10.00"));
        book1.setPublish("出版社101");
        book1.setStatus(1);
        bookInfoMapper.addBooks(book1);

        BookInfo book2 = new BookInfo();
        // book2.setId(102);
        book2.setBookName("图书102");
        book2.setAuthor("作者102");
        book2.setCount(20);
        book2.setPrice(new BigDecimal("20.00"));
        book2.setPublish("出版社102");
        book2.setStatus(1);
        bookInfoMapper.addBooks(book2);

        // 执行批量删除操作
        List<Integer> ids = Arrays.asList(101, 102);
        Integer result = bookInfoMapper.batchDelete(ids);
        assertEquals(2, result);

        // 验证删除结果
        assertNull(bookInfoMapper.QueryBooksBuId(101));
        assertNull(bookInfoMapper.QueryBooksBuId(102));
    }
}