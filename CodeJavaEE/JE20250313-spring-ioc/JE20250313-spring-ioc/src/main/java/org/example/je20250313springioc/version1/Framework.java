package org.example.je20250313springioc.version1;

/**
 * Created with IntelliJ IDEA.
 * Description: 车身
 * User: 姚东名
 * Date: 2025-03-13
 * Time: 20:34
 */
public class Framework {
    private Bottom bottom;
    public Framework(Integer size) {
        this.bottom = new Bottom(size);
        System.out.println("framework init, size:" + size);
    }
}
