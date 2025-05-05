package org.example.springblogdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springblogdemo.pojo.dataobject.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-05
 * Time: 10:10
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
