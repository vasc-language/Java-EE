package org.example.springblogdemo2.pojo.response;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-09
 * Time: 19:19
 */
@Data
public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
}
