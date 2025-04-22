package org.example.jd20250417springbookdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.jd20250417springbookdemo.enums.BookStatusEnum;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.example.jd20250417springbookdemo.model.PageRequest;
import org.example.jd20250417springbookdemo.model.ResponseResult;
import org.example.jd20250417springbookdemo.model.Result;
import org.example.jd20250417springbookdemo.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 22:46
 */
@Slf4j
@RequestMapping("/book")
@RestController
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping(value = "/addBook", produces = "application/json")
    public Result addBook(@RequestBody BookInfo bookInfo) {
        log.info("添加图书，request: {}", bookInfo);
        /**
         * 1. 参数校验
         * 2. 存储数据
         * 3. 返回结果
         */
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null) {
            log.warn("添加图书, 参数不合法, request: {}", bookInfo);
            
            // 检查各个参数并提供具体错误信息
            if (!StringUtils.hasLength(bookInfo.getBookName())) {
                return Result.fail("书名不能为空");
            }
            if (!StringUtils.hasLength(bookInfo.getAuthor())) {
                return Result.fail("作者不能为空");
            }
            if (bookInfo.getCount() == null) {
                return Result.fail("数量不能为空");
            }
            if (bookInfo.getPrice() == null) {
                return Result.fail("价格不能为空");
            }
            if (!StringUtils.hasLength(bookInfo.getPublish())) {
                return Result.fail("出版社不能为空");
            }
            if (bookInfo.getStatus() == null) {
                return Result.fail("状态不能为空");
            }
            
            return Result.fail("参数不合法");
        }
        try {
            bookInfoService.addBook(bookInfo);
            return Result.success(bookInfo.getId());
        } catch (Exception e) {
            log.error("添加图书异常, e: {}", e);
            return Result.fail("添加图书发生异常: " + e.getMessage());
        }
    }

    // 返回一个页码的中所有的内容
    @RequestMapping("/getListByPage")
    public Result<ResponseResult<BookInfo>> getListByPage(PageRequest pageRequest){
        ResponseResult<BookInfo> listByPage = bookInfoService.getListByPage(pageRequest);
        return Result.success(listByPage);
    }

    // 查询图书信息
//    public BookInfo queryBookById(Integer bookId) {
//              log.info("查询图书信息，bookId: ", bookId);
//              return bookInfoService.queryBookById(bookId);
    @RequestMapping("/queryBookById")
    public Result<BookInfo> queryBookById(@RequestParam Integer bookId) {
        log.info("查询图书信息，bookId: {}", bookId);
        BookInfo book = bookInfoService.queryBookById(bookId);
        return Result.success(book);
    }

    // 更新修改图书
//    @RequestMapping("/updateBook")
//    public Result updateBook(BookInfo bookInfo) {
    @PostMapping("/updateBook")
    public Result updateBook(@ModelAttribute BookInfo bookInfo) {
        log.info("修改图书， bookInfo: {}", bookInfo);
        try {
            bookInfoService.updateBook(bookInfo);
            // 成功
            return Result.success("");
        } catch (Exception e) {
            log.error("修改图书发生异常，e: {}", e);
            return Result.fail("修改图书发生异常");
        }
    }

    // 删除图书 -> 调用更新操作即可
//    @PostMapping("/deleteBook")
//    public Result deleteBook(@RequestParam Integer bookId) {
    @PostMapping("/deleteBook")
    public Result deleteBook(@RequestParam Integer bookId) {
        log.info("删除图书，bookId: {}", bookId);

        try {
            // 修改 Id 和 status 状态码
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(bookId);
            bookInfo.setStatus(BookStatusEnum.DELETE.getCode());
            // 更新
            bookInfoService.updateBook(bookInfo);
            // 删除成功
            return Result.success(""); // 成功返回，空字符串
        } catch (Exception e) {
            log.error("删除图书异常，e: {}", e);
            return Result.fail("删除图书发生异常");
        }
    }

    // 批量删除图书
    // 使用集合进行传递，需要进行参数绑定
    // 告诉 Spring：请从 URL 或表单里拿 ids 这批参数，按 Integer 列表的方式交给我 
    @PostMapping("/batchDelete")
    public Result batchDelete(@RequestParam("ids") List<Integer> ids) {
        log.info("批量删除图书, ids:{}", ids);
        try {
            bookInfoService.batchDelete(ids);
            return Result.success(true);
        } catch (Exception e) {
            log.error("批量删除图书失败, e:{}", e);
            return Result.fail("批量删除图书失败");
        }
    }
}
