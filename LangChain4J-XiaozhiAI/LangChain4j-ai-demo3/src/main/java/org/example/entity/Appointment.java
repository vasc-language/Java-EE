package org.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description: 预约业务的实现
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 18:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Appointment {
    @TableId(type = IdType.AUTO) // 设置 Id 为自增主键
    private Long id;
    private String username;
    private String idCard;
    private String department;
    private String date;
    private String time;
    private String doctorName;
}
