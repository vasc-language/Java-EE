package org.example.springblogdemo.pojo.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: API 响应对象
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:40
 */
@Data
public class BlogInfoResponse {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
}
