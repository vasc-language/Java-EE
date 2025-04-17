package org.example.jd20250417springbookdemo.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-17
 * Time: 21:07
 */
@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private BigDecimal price;
    private String publish;
    private Integer status; // 0-删除 1-正常 2-不可借阅
    private String statusCN; // 设置状态(将数字转化为字符串)
    private Date createTime;
    private Date updateTime;
}
