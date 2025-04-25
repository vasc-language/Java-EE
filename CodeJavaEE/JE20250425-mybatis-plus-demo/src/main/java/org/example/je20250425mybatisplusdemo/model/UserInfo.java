package org.example.je20250425mybatisplusdemo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 22:45
 */
@Data
@TableName("user_info")
public class UserInfo {
    // 设置唯一主键
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private Integer gender;
    private String phone;
    @TableField("delete_flag")
    private Integer deleteFlag;
    @TableField("create_time")
    private Date createTime;
    @TableField("update_time")
    private Date updateTime;
}
