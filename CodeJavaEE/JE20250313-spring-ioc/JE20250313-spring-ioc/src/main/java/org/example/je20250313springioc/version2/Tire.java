package org.example.je20250313springioc.version2;

/**
 * Created with IntelliJ IDEA.
 * Description: 轮胎
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:34
 */
public class Tire {
    int size;
    String color;
    public Tire(Integer size, String color) {
        this.size = size;
        this.color = color;
        System.out.println("tire init, size:" + size + " color:" + color);
    }
}
