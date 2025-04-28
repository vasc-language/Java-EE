package org.study.je20250428springaopdemo.proxy;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 9:13
 */
public class Main {
    public static void main(String[] args) {
        HouseSubject target = new RealHouseSubject(); // 真实的房东
        // 1. 静态代理
        HouseSubject houseSubject = new HouseProxy(target); // 中介代理
        houseSubject.saleHouse(); // 代理卖房子
        // houseSubject.rentHouse(); // 代理出租房子

        // 2. JDK 动态代理
        // 创建一个代理类
        /**
         *  public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)
         *  - ClassLoader loader：类加载器，用于定义生成的代理类，通常使用目标对象的类加载器 target.getClass().getClassLoader()
         *  - Class<?>[] interfaces：一个包含代理类需要实现的接口的 Class 对象数组。关键点：JDK 动态代理要求目标方法必须实现至少一个接
         *    口，代理类会实现这些指定的接口。传入 new Class[]{HouseSubject.class} 表示生成的代理类需要实现 HouseSubject 接口
         *  - InvocationHandler h：调用处理器。这是一个实现了 InvocationHandle 接口的对象。所有的对代理对象的方法调用最终都会被转发到
         *    这个处理器的 invoke 方法。在 Main.java new JDKInvocation(target) 的实例
         */
        HouseSubject houseProxy = (HouseSubject) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                new Class[]{HouseSubject.class}, new JDKInvocation(target));
        /**
         * - 获取目标对象的类加载器
         * - 指定代理对象需要实现 HouseSubject 接口
         * - 创建一个 JDKInvocation 实例，并将 target 对象传递给它，这样就知道了要代理哪个真实对象
         * - Proxy.newProxyInstance 在内存中动态生成一个新的类，这个类实现了 HouseSubject 接口，并且它的所有方法调用都会被路由到我们提
         *  供的 JDKInvocation 实例的 invoke 方法
         */
        houseProxy.saleHouse();

        // 3. CGlib 动态代理
        /**
         * Enhance 类是 CGLIB 中用于创建代理对象的主类，可以看成一个工厂，用来配置和生成代理类
         * create() 根据配置生成并返回代理对象的实例
         */
        HouseSubject houseProxy2 = (HouseSubject) Enhancer.create(target.getClass(), new CGlibMethodInterceptor(target));
        /**
         *
         */
        houseProxy.rentHouse();
    }

}
