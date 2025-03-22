package com.example.je20250306springbook.service;

import com.example.je20250306springbook.Dao.BookDao;
import com.example.je20250306springbook.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 9:38
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public List<BookInfo> getList() {

        List<BookInfo> bookInfos = bookDao.mockData();
        //对结果进行二次处理
        for (BookInfo bookInfo : bookInfos) {
            if (bookInfo.getStatus() == 1) {
                bookInfo.setStatusCN("可借阅");
            } else {
                bookInfo.setStatusCN("不可借阅");
            }
        }
        return bookInfos;
    }
}

