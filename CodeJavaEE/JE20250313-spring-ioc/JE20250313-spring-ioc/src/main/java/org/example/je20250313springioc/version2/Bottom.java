package org.example.je20250313springioc.version2;

/**
 * Created with IntelliJ IDEA.
 * Description: 底盘
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:34
 */

public class Bottom {
    private Tire tire;
    public Bottom(Tire tire) {
        this.tire = tire;
        System.out.println("bottom init!!!");
    }
}
