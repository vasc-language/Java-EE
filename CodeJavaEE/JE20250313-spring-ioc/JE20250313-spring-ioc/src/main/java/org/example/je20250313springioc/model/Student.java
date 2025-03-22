package org.example.je20250313springioc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-03-20
 * Time: 21:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student {
    // 注释代替 有参和无参的构造方法、Setter()、getter()
    private String name;
    private Integer age;
}
