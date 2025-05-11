package org.example.springblogdemo2.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-11
 * Time: 8:53
 */
@Data
public class UpdateRequest {
    // id title content
    @NotNull(message = "id 不能为 null")
    private Integer id;
    private String title;
    private String content;
}
