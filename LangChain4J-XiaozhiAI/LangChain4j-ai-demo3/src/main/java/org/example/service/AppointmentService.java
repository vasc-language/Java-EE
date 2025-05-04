package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.entity.Appointment;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 18:19
 */

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}
