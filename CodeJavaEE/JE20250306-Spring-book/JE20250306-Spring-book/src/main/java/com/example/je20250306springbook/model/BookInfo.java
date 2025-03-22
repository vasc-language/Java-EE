package com.example.je20250306springbook.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-06
 * Time: 8:50
 */
// @Data
// @Getter @Setter
public class BookInfo {
    private Integer bookId;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publish;
    private Integer status; // 1-可订阅 2-不可订阅
    private String statusCN;

    public BookInfo() {
    }

    public BookInfo(Integer bookId, String bookName, String author, Integer num, BigDecimal price, String publish, Integer status, String statusCN) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.num = num;
        this.price = price;
        this.publish = publish;
        this.status = status;
        this.statusCN = statusCN;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusCN() {
        return statusCN;
    }

    public void setStatusCN(String statusCN) {
        this.statusCN = statusCN;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", num=" + num +
                ", price=" + price +
                ", publish='" + publish + '\'' +
                ", status=" + status +
                ", statusCN='" + statusCN + '\'' +
                '}';
    }
}
