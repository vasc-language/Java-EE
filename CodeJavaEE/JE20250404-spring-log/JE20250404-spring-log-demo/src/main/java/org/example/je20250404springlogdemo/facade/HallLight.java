package org.example.je20250404springlogdemo.facade;

/**
 * Created with IntelliJ IDEA.
 * Description: 走廊的灯
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:49
 */
public class HallLight implements Light {
    @Override
    public void on() {
        System.out.println("打开走廊的灯");
    }

    @Override
    public void off() {
        System.out.println("关闭走廊的灯");
    }
}
