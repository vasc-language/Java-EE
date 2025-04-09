package org.example.je20250404springlogdemo.facade;

/**
 * Created with IntelliJ IDEA.
 * Description:卧室的灯
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:49
 */
public class BedroomLight implements Light {
    @Override
    public void on() {
        System.out.println("打开卧室的灯");
    }

    @Override
    public void off() {
        System.out.println("关闭卧室的灯");
    }
}
