package org.example.je20250404springlogdemo.facade;

/**
 * Created with IntelliJ IDEA.
 * Description:客厅的灯
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:48
 */
public class LivingRoomLight implements Light {
    @Override
    public void on() {
        System.out.println("打开客厅的灯");
    }

    @Override
    public void off() {
        System.out.println("关闭客厅的灯");
    }
}
