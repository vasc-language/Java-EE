package org.example.jd20250417springbookdemo.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 将页面封装成一个对象
 * User: 姚东名
 * Date: 2025-04-19
 * Time: 15:56
 */
@Data
public class PageRequest {
    private Integer currentPage = 1; // 当前页面：1、2、3（因为只有30条数据）
    private Integer pageSize = 10; // 一个页面呈现10条数据
    private Integer offset; // 页面的起始页码 (currentPage - 1) * pageSize

    // 求当前页面的起始页码
    public Integer getOffset() {
        return (currentPage - 1) * pageSize;
    }
}
