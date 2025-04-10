package org.example.jd20250410mybatisdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-10
 * Time: 7:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    private Integer deleteFlag;
    private Date createTime;
    private Date updateTime;
}
