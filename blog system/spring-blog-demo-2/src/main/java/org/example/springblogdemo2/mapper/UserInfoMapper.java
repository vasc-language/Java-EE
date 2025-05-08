package org.example.springblogdemo2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.springblogdemo2.pojo.dataobject.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-08
 * Time: 21:48
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
