package org.example.springblogdemo2.pojo.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserInfo {
  @TableId(type = IdType.AUTO)
  private Integer id;
  private String userName;
  private String password;
  private String githubUrl;
  private Integer deleteFlag;
  private LocalDate createTime;
  private LocalDate updateTime;
}
