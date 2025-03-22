package org.example.je20250307springbook.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 20:24
 */
@Data
public class UserInfo {
    private Integer bookId;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publish;
    private Integer status;
    private String statusCN;
}
