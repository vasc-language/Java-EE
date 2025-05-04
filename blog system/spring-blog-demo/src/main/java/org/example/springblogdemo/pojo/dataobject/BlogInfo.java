package org.example.springblogdemo.pojo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-04
 * Time: 21:34
 */
@Data
public class BlogInfo {
    @TableId(type = IdType.AUTO)
    private Integer id; // 主键自增
    private String title;
    private String content;
    private Integer deleteFlag;
    private Date createTime;
    private LocalDateTime updateTime;
}
