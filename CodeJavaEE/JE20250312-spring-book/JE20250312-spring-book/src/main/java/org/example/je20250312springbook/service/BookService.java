package org.example.je20250312springbook.service;

import org.example.je20250312springbook.dao.BookDao;
import org.example.je20250312springbook.mode.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description: 业务处理层：处理借阅状态
 * User: 姚东名
 * Date: 2025-03-12
 * Time: 21:15
 */

/**
 * 具象化解释：@Controller + @Repository + @Service + @Autowired 之间是怎么各司其职但又相互配合
 * 顾客请求 -> 服务员(@Controller) -> 厨师(@Service) <-- @Autowired(自动连接) --> 仓库管理员(@Repository) <--> 食材仓库(Database)
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    //二次处理借阅状态 1-可借阅 2-不可借阅
    public List<BookInfo> getList() {
        List<BookInfo> bookInfos = bookDao.mockData();
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
