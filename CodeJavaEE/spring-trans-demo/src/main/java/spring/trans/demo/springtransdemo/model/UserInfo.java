package spring.trans.demo.springtransdemo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 17:40
 */
@Data
public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
}
