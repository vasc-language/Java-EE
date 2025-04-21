package org.example.jd20250417springbookdemo.service;

import org.example.jd20250417springbookdemo.enums.BookStatusEnum;
import org.example.jd20250417springbookdemo.mapper.BookInfoMapper;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.example.jd20250417springbookdemo.model.PageRequest;
import org.example.jd20250417springbookdemo.model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 处理分页情况
    public ResponseResult<BookInfo> getListByPage(PageRequest pageRequest) {
        // 1. 获取页码总数
        // 2. 获取当前页面的数据
        Integer count = bookInfoMapper.count();

        List<BookInfo> bookInfos = bookInfoMapper.selectBooksByPage(pageRequest);
        // 对结果进行二次处理 ——> 处理状态码
        // 0-删除 1-正常 2-不可借阅
        for (BookInfo bookInfo: bookInfos) {
            // 前端展示时就能直接显示中文状态
            bookInfo.setStatusCN(BookStatusEnum.getStatusByCode(bookInfo.getStatus()).getDesc());
        }
        // 返回分页结果对象
        return new ResponseResult<>(count, bookInfos, pageRequest);
    }

    // 返回更新后的数据
    public BookInfo queryBookById(Integer bookId) {
        return bookInfoMapper.QueryBookById(bookId);
    }

    // 更新修改图书
    public void updateBook(BookInfo bookInfo) {
        bookInfoMapper.updateBook(bookInfo);
    }

    //  删除图书 -> 直接调用了 更新操作的方法


    // 批量删除图书
    public Integer batchDelete(List<Integer> ids) {
        return bookInfoMapper.batchDelete(ids);
    }

}
