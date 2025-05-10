package org.example.springblogdemo2.pojo.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.example.springblogdemo2.common.util.DateUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:32
 */
@Data
public class BlogInfoResponse {
    private Integer id;
    private String title;
    private String content;
    private Integer userId; // 作者 id

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    public String getCreateTime() {
        return DateUtils.dateFormat(createTime);
    }
    
    // 添加一个表示当前时间的字段
    public String getCurrentTime() {
        return DateUtils.dateFormat(new Date());
    }
}
