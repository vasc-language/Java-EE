package org.example.je20250313springioc.component;

import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-20
 * Time: 21:27
 */
@Component
public class UserComponent {
    public void print() {
        System.out.println("do component");
    }
}
