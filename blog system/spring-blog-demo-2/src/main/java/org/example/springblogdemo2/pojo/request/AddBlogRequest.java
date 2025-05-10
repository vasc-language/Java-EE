package org.example.springblogdemo2.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-10
 * Time: 11:03
 */
@Data
public class AddBlogRequest {
    @NotNull(message = "userId 不能为 null")
    private Integer userId;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;
}
