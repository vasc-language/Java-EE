package org.example.jd20250417springbookdemo.service;

import org.example.jd20250417springbookdemo.mapper.BookInfoMapper;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 22:44
 */
@Service
public class BookInfoService {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    // 插入数据
    public void addBook(BookInfo bookInfo) {
        bookInfoMapper.addBook(bookInfo);
    }
}
