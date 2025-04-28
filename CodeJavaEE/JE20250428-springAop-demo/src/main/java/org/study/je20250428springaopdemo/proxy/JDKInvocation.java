package org.study.je20250428springaopdemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description: 动态代理方式
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 20:54
 */

/**
 * JDK 动态代理
 * JDK 动态代理是 Java 提供的一种在运行时动态创建代理类和实例的机制。与静态代理需要在编译时就为每个目标类编写一个
 * 代理类不同，动态代理允许我们在程序运行时，根据一组接口动态地生成一个实习了这些接口地代理对象。当通过代理对象调用
 * 接口方法时，这些调用会被统一转发到一个 InvocationHandler 对象的 invoke 方法中处理
 */
public class JDKInvocation implements InvocationHandler {
    // 目标方法，被代理对象
    private Object target;

    public JDKInvocation(Object target) {
        this.target = target;
    }
    // 调用目标方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我开始代理~");
        Object invoke = method.invoke(target, args);
        System.out.println("我结束代理~");
        return invoke;
    }
}
