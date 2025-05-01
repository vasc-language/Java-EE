package spring.trans.demo.springtransdemo.model;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 17:41
 */
@Data
public class LogInfo {
    private Integer id;
    private String userName;
    private String op;
    private Date createTime;
    private Date updateTime;
}
