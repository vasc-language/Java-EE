package org.example.je20250313springioc.version2;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:54
 */
// 利用
public class Main {
    public static void main(String[] args) {
        // 顺序：Tire -> Bottom -> Framework -> Car
        // 相当于是外包公司，解耦合
        Tire tire = new Tire(20, "紫色");
        Bottom bottom = new Bottom(tire);
        Framework framework = new Framework(bottom);
        Car car = new Car(framework);

        car.run(); // 开始启动
    }
}
