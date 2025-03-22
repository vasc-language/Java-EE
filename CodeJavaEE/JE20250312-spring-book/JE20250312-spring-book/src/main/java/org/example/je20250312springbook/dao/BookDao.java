package org.example.je20250312springbook.dao;

import org.example.je20250312springbook.mode.BookInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-12
 * Time: 21:04
 */
@Repository
public class BookDao {
    // mock(模拟) 从数据库中查询数据
    public List<BookInfo> mockData() {
        List<BookInfo> bookInfos = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            BookInfo bookInfo = new BookInfo();

            bookInfo.setBookId(i);
            bookInfo.setBookName("图书" + i);
            bookInfo.setPublish("出版社" + i);
            bookInfo.setAuthor("作者"+i);
            bookInfo.setNum(new Random().nextInt(100)); // 生成 0~99 随机数
            bookInfo.setPrice(new BigDecimal(new Random().nextInt(100)));
            bookInfo.setStatus(i % 5 == 0 ? 2 : 1); // 1-可借阅 2-不可借阅

            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }
}
