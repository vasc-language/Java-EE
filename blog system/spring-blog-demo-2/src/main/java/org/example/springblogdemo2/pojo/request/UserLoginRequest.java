package org.example.springblogdemo2.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 19:17
 */
@Data
public class UserLoginRequest {
    /**
     * 这些校验注释是哪些异常进行处理的
     * 1. 当使用 @Valid 或 @Validated 注释标记控制器方法参数
     * 2. 当请求参数数据绑定到这些带注释的对象
     */
    @NotNull(message = "用户名不能为 null")
    private String userName;

    @NotNull(message = "密码不能为 null")
    @Length(min = 5, max = 11)
    private String password;
}
