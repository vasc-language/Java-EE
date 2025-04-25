package org.example.je20250425mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.je20250425mybatisplusdemo.model.UserInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-25
 * Time: 22:54
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    // selectById
    @Select("SELECT * FROM `user_info` WHERE id = #{id}")
    UserInfo selectById(Integer id);
}
