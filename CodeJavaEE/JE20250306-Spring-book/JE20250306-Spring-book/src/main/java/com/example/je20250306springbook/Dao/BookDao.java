package com.example.je20250306springbook.Dao;

import com.example.je20250306springbook.model.BookInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 9:25
 */
@Repository
public class BookDao {
    // 伪造数据进行测试
    public List<BookInfo> mockData() {
        List<BookInfo> bookInfoList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setBookId(i);
            bookInfo.setBookName("图书" + i);
            bookInfo.setAuthor("作者" + i);
            // bookInfo.setNum(i);
            bookInfo.setPublish("出版社" + i);
            bookInfo.setStatus(i % 5 == 0 ? 2 : 1); // 1 可借阅 2 不可借阅

            bookInfo.setNum(new Random().nextInt(100));
            bookInfo.setPrice(new BigDecimal (new Random().nextInt(100)));
            // 把模拟数据放回
            bookInfoList.add(bookInfo);
        }
        return bookInfoList;
    }
}
