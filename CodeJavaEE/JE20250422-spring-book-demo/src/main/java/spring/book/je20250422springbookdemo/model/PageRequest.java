package spring.book.je20250422springbookdemo.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-23
 * Time: 20:08
 */
@Data
public class PageRequest {
    // 将分页的属性封装成一个对象
    private Integer currentPage = 1; // 当前页面（1、2、3）
    private Integer pageSize = 10; // 当前页面有多少页码（10个进度条）
    private Integer offset; // 当前页面的起始页码

    // 求当前页的起始页码
    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
