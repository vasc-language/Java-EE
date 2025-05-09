package org.example.springblogdemo2.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 19:18
 */
@AllArgsConstructor
@Data
public class UserLoginResponse {
    private Integer userId;
    private String token;
}
