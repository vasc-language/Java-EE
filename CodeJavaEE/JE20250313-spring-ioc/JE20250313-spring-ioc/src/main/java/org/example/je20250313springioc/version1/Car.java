package org.example.je20250313springioc.version1;

/**
 * Created with IntelliJ IDEA.
 * Description: 汽车
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:32
 */
public class Car {
    private Framework framework;
    public Car(Integer size) {
        this.framework = new Framework(size);
    }
    public void run() {
        System.out.println("car run...");
    }
}
