package spring.book.je20250422springbookdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.book.je20250422springbookdemo.enums.BookStatusEnum;
import spring.book.je20250422springbookdemo.mapper.BookInfoMapper;
import spring.book.je20250422springbookdemo.model.BookInfo;
import spring.book.je20250422springbookdemo.model.PageRequest;
import spring.book.je20250422springbookdemo.model.ResultResponse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 7:25
 */
@Service
public class BookInfoService {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    // 添加图书
    public void addBooks(BookInfo bookInfo) {
        bookInfoMapper.addBooks(bookInfo);
    }

    // 分页操作
    public ResultResponse<BookInfo> selectBooksByPage(PageRequest pageRequest) {
        // 1. 获取总记录数（total）
        Integer count = bookInfoMapper.count();

        // 2. 获取当前页面所有的内容（records）
        List<BookInfo> bookInfos = bookInfoMapper.selectBooksByPage(pageRequest);

        // 3. 对结果的状态码进行二次处理(code, desc)
        // 0-删除 1-正常 2-不允许借阅
        for (BookInfo bookInfo : bookInfos) {
            bookInfo.setStatusCN(BookStatusEnum.getStatusByCode(bookInfo.getStatus()).getDesc());
        }

        // 4. 返回分页结果
        // total、bookInfo 对象（包含了id、userName、author 等属性）、pageRequest 对象（包含 currentPage、pageSize、offset 属性）
        return new ResultResponse<>(count, bookInfos, pageRequest);
    }

    // 返回更新后的数据
    public BookInfo queryBooksBuId(Integer bookId) {
        return bookInfoMapper.QueryBooksBuId(bookId);
    }

    // 更新图书数据
    public void updateBooks(BookInfo bookInfo) {
        bookInfoMapper.updateBooks(bookInfo);
    }

    // 批量删除图书
    // List<Integer> ids 是 通过 Id 来进行标识，就使用一个集合装 id 属性
    public Integer batchDelete(List<Integer> ids) {
        return bookInfoMapper.batchDelete(ids);
    }
}
