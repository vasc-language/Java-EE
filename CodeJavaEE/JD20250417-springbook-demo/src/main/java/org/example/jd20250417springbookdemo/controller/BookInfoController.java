package org.example.jd20250417springbookdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.example.jd20250417springbookdemo.model.PageRequest;
import org.example.jd20250417springbookdemo.model.ResponseResult;
import org.example.jd20250417springbookdemo.model.Result;
import org.example.jd20250417springbookdemo.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    // 更新修改图书
    @RequestMapping("/updateBook")
    public Result updateBook(BookInfo bookInfo) {
        log.info("修改图书， bookInfo: {}", bookInfo);
        try {
            bookInfoService.updateBook(bookInfo);
            // 成功
            return Result.success("");
        } catch (Exception e) {
            log.error("修改图书发生异常，e: ", e);
            return Result.fail("修改图书发生异常");
        }
    }

}
