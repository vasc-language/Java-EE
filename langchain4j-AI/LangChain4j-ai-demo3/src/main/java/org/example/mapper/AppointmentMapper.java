package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.entity.Appointment;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 18:13
 */
@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
    // myBatis-plus 实现的
}
