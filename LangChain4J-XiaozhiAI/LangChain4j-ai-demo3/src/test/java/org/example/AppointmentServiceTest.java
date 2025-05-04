package org.example;

import org.example.entity.Appointment;
import org.example.service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-03
 * Time: 18:35
 */
@SpringBootTest
public class AppointmentServiceTest {
    @Autowired
    private AppointmentService appointmentService;

    /**
     * 从数据库中获取用户信息
     */
    @Test
    void testGetOne() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("2025050301");
        appointment.setDepartment("内科");
        appointment.setDate("2025-05-03");
        appointment.setTime("上午");

        // 从数据库中查询是否存在张三（appointment ）这个实体类的信息
        Appointment appointmentDB = appointmentService.getOne(appointment); // getOne() 方法是自己实现的
        System.out.println(appointmentDB);
    }

    /**
     * 将用户信息存储到数据库中
     */
    @Test
    void testSave() {
        Appointment appointment = new Appointment();
        appointment.setUsername("张三");
        appointment.setIdCard("2025050301");
        appointment.setDepartment("内科");
        appointment.setDate("2025-05-03");
        appointment.setTime("上午");
        appointment.setDoctorName("张医生");

        // save() 是 MyBatis-plus 中 IService 实现好的方法
        appointmentService.save(appointment);
    }

    /**
     * 根据ID删除数据库中用户信息
     */
    @Test
    void testRemove() {
        // remove() 是 MyBatis-plus 中 IService 实现好的方法
        appointmentService.removeById(1L);
    }
}
