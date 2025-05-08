package org.example.springblogdemo.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-06
 * Time: 11:34
 */
@AllArgsConstructor
@Data
public class UserLoginResponse {
    private Integer userId; // 用户 ID
    private String token; // 令牌，用于校验身份
}
