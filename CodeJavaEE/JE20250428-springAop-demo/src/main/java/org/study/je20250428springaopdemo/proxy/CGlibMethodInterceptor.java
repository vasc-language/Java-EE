package org.study.je20250428springaopdemo.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * Description: 动态代理方法
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 21:10
 */

/**
 * MethodInterceptor 是 CGLIB 的核心回调接口，类似于 JDK 动态代理中的 InvocationHandler 你需要创建一个一个类来实现这个接口
 *
 */
public class CGlibMethodInterceptor implements MethodInterceptor {
    // 目标对象，被代理对象
    private Object target;

    public CGlibMethodInterceptor(Object target) {
        this.target = target;
    }

    /**
     * @param obj 指代 CGlib生成的代理对象实例（即目标类的子类实例）
     * @param method 代表被拦截的目标方法的对象（RealHouseSubject 类中的 saleHouse() 或 rentHouse() 方法）
     * @param args 包含调用目标方法时传递的参数的数组
     * @param proxy 这是 CGLIB 特有的一个代理对象，它通常用来调用父类（也就是原始目标类）的同名方法
     * @return Object intercept 方法的返回值会作为代理对象方法调用的返回值
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("我开始代理~");
        // Object invoke = method.invoke(target, args);
        // obj 是代理对象，调用父类（RealHouseSubject）的原始方法
        Object invoke = proxy.invokeSuper(target, args);
        System.out.println("我结束代理~");
        return invoke;
    }
}
