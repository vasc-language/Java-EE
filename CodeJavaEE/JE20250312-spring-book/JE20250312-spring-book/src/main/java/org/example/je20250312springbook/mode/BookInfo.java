package org.example.je20250312springbook.mode;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-12
 * Time: 19:49
 */
@Data
public class BookInfo {
    private Integer bookId;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publish;
    private Integer status;
    private String statusCN;
}
