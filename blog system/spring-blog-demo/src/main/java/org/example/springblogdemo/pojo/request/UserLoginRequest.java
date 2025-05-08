package org.example.springblogdemo.pojo.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * Created with IntelliJ IDEA.
 * Description: 前端发送过来的请求
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 11:29
 */
@Data
public class UserLoginRequest {
    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 20)
    private String userName;

    @NotNull(message = "密码不能为空")
    private String password;
}
