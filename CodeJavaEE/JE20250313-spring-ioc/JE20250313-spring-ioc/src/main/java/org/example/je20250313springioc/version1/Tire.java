package org.example.je20250313springioc.version1;

/**
 * Created with IntelliJ IDEA.
 * Description: 轮胎
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:34
 */
public class Tire {
    int size;
    public Tire(Integer size) {
        this.size = size;
        System.out.println("tire init, size:" + size);
    }
}
