package spring.book.je20250422springbookdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-22
 * Time: 20:56
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer count;
    private BigDecimal price;
    private String publish;
    private Integer status; // 0-删除 1-正常 2-不可借阅
    private String statusCN; // 设置状态(0, "删除") (code, desc)
    private Date createTime;
    private Date updateTime;
}
