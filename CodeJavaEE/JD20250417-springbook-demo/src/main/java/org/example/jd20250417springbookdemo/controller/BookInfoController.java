package org.example.jd20250417springbookdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.jd20250417springbookdemo.model.BookInfo;
import org.example.jd20250417springbookdemo.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 22:46
 */
@Slf4j
@RequestMapping("book")
@RestController
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;

    @PostMapping(value = "/addBook", produces = "application/json")
    public String addBook(@RequestBody BookInfo bookInfo) {
        log.info("添加图书，request: {}", bookInfo);
        /**
         * 1. 参数校验
         * 2. 存储数据
         * 3. 返回结果
         */
//        if (!StringUtils.hasLength(bookInfo.getBookName())) {
//            log.warn("添加图书，bookName 参数不合法，request: {}", bookInfo);
//            return "bookName 参数不合法";
//        }
//        if (!StringUtils.hasLength(bookInfo.getAuthor())) {
//            log.warn("添加图书，author 参数不合法，request: {}", bookInfo);
//            return "author 参数不合法";
//        }
//        if (bookInfo.getCount() == null || bookInfo.getCount() <= 0) {
//            log.warn("添加图书，count 参数不合法，request: {}", bookInfo);
//            return "count 参数不合法";
//        }
////        if (!StringUtils.hasLength(bookInfo.getPrice())) {
////            log.warn("添加图书，参数不合法，request: {}", bookInfo);
////            return "price 参数不合法";
////        }
//        if (!StringUtils.hasLength(bookInfo.getPublish())) {
//            log.warn("添加图书，参数不合法，request: {}", bookInfo);
//            return "publish 参数不合法";
//        }
////        if (!StringUtils.hasLength(bookInfo.getStatus())) {
////            log.warn("添加图书，参数不合法，request: {}", bookInfo);
////            return "status 参数不合法";
////        }
        if (!StringUtils.hasLength(bookInfo.getBookName())
                || !StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null){
            log.warn("添加图书, 参数不合法, request: {}", bookInfo);
            return "参数不合法";
        }

        try {
            // 存储数据
            bookInfoService.addBook(bookInfo);
            return "添加图书成功";
        } catch (Exception e) {
            log.error("添加图书异常，e: {}", e);
            return "添加图书发生异常";
        }
    }
}
