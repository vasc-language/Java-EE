package org.example.je20250313springioc.version1;

/**
 * Created with IntelliJ IDEA.
 * Description: 底盘
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:34
 */
public class Bottom {
    private Tire tire;
    public Bottom(Integer size) {
        this.tire = new Tire(size);
        System.out.println("bottom init, size:" + size);
    }
}
