package org.example.jd20250410mybatisdemo.model;

import java.util.Date;

import lombok.Data;

@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer uid;
    private Integer deleteFlag;
    private Date createtime;
    private Date updateTime;
}
