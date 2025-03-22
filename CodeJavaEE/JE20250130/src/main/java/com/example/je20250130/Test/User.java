package com.example.je20250130.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-02-12
 * Time: 17:01
 */


/**
 * Lombok 简介
 * Lombok 是一个 Java 库，它通过注释来生成 Java 类的样板代码，例如：getter setting hashCode equals toString
 * 构造器等等，使用 Lombok 可以大大减少代码量，提高开发效率，并使代码更加清晰易读。
 *
 * @Data 的作用
 * 它是一个组合注释，相当于使用了以下几个注释：
 * 1. @Getter：为类中所有字段生成 getter 方法。
 * 2. @Setter：为类中所有字段生成 setter 方法。
 * 3. @ToString：为类生成 toString() 方法，默认情况下会输出类名，字段名和字段值。
 * 4. @EqualsAndHashCode：为类生成 equals() 和 hashCode() 方法，默认情况下会使用非静态和非瞬态的字段生成。
 * 5. @RequiredArgsConstructor：为所有的 final 字段和 @NonNull 注释生成构造器。
 * 简而言之，@Data 注释会为你生成带有 Getter() Setter() toString() equals() hashCode() 和构造器（根据 final 和 NonNull 字段）
 */
@Data // 使用 @Data 注解
@AllArgsConstructor @ToString
public class User {
    private String name;
    private int age;
}
