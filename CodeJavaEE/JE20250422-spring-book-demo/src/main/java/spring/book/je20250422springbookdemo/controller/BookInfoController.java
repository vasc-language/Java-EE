package spring.book.je20250422springbookdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import spring.book.je20250422springbookdemo.enums.BookStatusEnum;
import spring.book.je20250422springbookdemo.model.BookInfo;
import spring.book.je20250422springbookdemo.model.PageRequest;
import spring.book.je20250422springbookdemo.model.Result;
import spring.book.je20250422springbookdemo.model.ResultResponse;
import spring.book.je20250422springbookdemo.service.BookInfoService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 7:28
 */
@Slf4j
@RequestMapping("/book")
@RestController
public class BookInfoController {
    @Autowired
    private BookInfoService bookInfoService;

    // 插入图书
    @RequestMapping(value = "/addBooks", produces = "application/json")
    public Result addBooks(@RequestBody BookInfo bookInfo) {
        log.info("插入图书数据，request: {}", bookInfo);
        /**
         * 1. 参数校验
         * 2. 存储数据
         * 3. 返回结果
         */

        if (!StringUtils.hasLength(bookInfo.getBookName())
                ||!StringUtils.hasLength(bookInfo.getAuthor())
                || bookInfo.getCount() == null
                || bookInfo.getPrice() == null
                || !StringUtils.hasLength(bookInfo.getPublish())
                || bookInfo.getStatus() == null
        ) {
            log.warn("添加图书，参数不合法，request: {}", bookInfo);
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
            bookInfoService.addBooks(bookInfo);
            return Result.success(bookInfo.getId());
        } catch (Exception e) {
            log.error("添加图书失败，e: {}", e);
            return Result.fail("添加图书异常: " + e.getMessage());
        }
    }

    // 分页操作
    @RequestMapping("/getListByPage")
    public Result<ResultResponse<BookInfo>> getListByPage(PageRequest pageRequest) {
        ResultResponse<BookInfo> bookInfoResultResponse = bookInfoService.selectBooksByPage(pageRequest);
        return Result.success(bookInfoResultResponse);
    }

    // 返回更新后的图书数据
    @RequestMapping("/queryBookById")
    public Result<BookInfo> queryBookById(@RequestParam Integer bookId) {
        log.info("查询图书信息：bookId: {}", bookId);
        BookInfo bookInfo = bookInfoService.queryBooksBuId(bookId);
        return Result.success(bookInfo);

    }

    // 更新图书信息
    @RequestMapping("/updateBooks")
    public Result updateBooks(@ModelAttribute BookInfo bookInfo) {
        log.info("更新图书, bookInfo: {}", bookInfo);
        try {
            bookInfoService.updateBooks(bookInfo);
            // 更新操作成功
            return Result.success("");
        } catch (Exception e) {
            log.error("更新图书发生异常，e: ", e);
            return Result.fail("更新图书发生异常");
        }
    }

    // 删除图书信息
    @RequestMapping("/deleteBooks")
    public Result deleteBooks(@RequestParam Integer bookId) {
        log.info("删除图书，bookId:c {}", bookId);
        try {
            BookInfo bookInfo = new BookInfo();
            // 修改 Id 和 status 的编号
            // 通过 Id 找到要删除的数据条，使用其中的 status = 0 ，从而实现逻辑上的删除
            bookInfo.setId(bookId);
            bookInfo.setStatus(BookStatusEnum.DELETE.getCode());

            // 更新
            bookInfoService.updateBooks(bookInfo);
            // 删除成功
            return Result.success("删除成功");
        } catch (Exception e) {
            log.error("删除图书发生异常，e: {}", e);
            return Result.fail("删除图书发生异常");
        }
    }

    // 批量删除图书
    @RequestMapping("/batchDeleteBooks")
    public Result batchDeleteBooks(@RequestParam("ids") List<Integer> ids) {
        log.info("批量删除图书，ids: {}", ids);

        try {
            bookInfoService.batchDelete(ids);
            return Result.success(true);
        } catch (Exception e) {
            log.error("批量删除图书发生异常，ids: {}", ids);
            return Result.fail("批量删除图书发生异常");
        }
    }

}
