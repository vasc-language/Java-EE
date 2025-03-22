/**
 * Created with IntelliJ IDEA.
 * Description: 懒汉模式与多线程的关系
 * User: 姚东名
 * Date: 2024-12-15
 * Time: 15:50
 */

/**
 * 线程安全问题
 * 简单的懒汉模式在多线程存在线程隐患。因为它是在第一次访问获取单例对象的方法（如getInstance）使才创建单例对象
 * 如果是多个线程同时调用这个方法，并且在检查单例对象是否创建（如instance == null），都发现对象尚未创建，那么
 * 这些线程就都会尝试去创建单例对象，这就会导致创建多个实例，这不符合单例模式的初衷
 */
class SingletonLazy1 {
    // 私有静态变量，用于存储单例的对象，初始值为null
    // 声明为volatile，确保多线程环境下的可见性和禁止指令性重排序
    private static volatile SingletonLazy1 instance = null;
    private static Object locker = new Object();
    // 私有构造方法，防止外部通过new 关键字创建实例
    private SingletonLazy1() {};
    // 公共静态方法，用于获取单例模式
    public static SingletonLazy1 getInstance() {
        // 采用双重检查锁定（DCL）
        if (instance == null) {
            synchronized (locker) {
                if (instance == null) {
                    instance = new SingletonLazy1();
                }
            }
        }
        return instance;
    }
}

/**
 * 线程安全解决方案与性能权衡 总结
 * 为了解决懒汉模式的线程安全问题，可以采用多种方法。
 *
 * 使用synchronized关键字修饰获取单例对象的方法可以保证线程安全。
 * 但是这种方式会带来性能问题，因为每次调用该方法时都需要获取锁，
 * 在高并发的多线程场景下，这会导致频繁的线程等待，增加系统的开销，降低程序的执行效率。
 *
 * 另一种方式是采用双重检查锁定（DCL），通过结合volatile关键字和两次检查单例对象是否已创建的机制，
 * 在保证线程安全的同时，减少了不必要的同步开销，提高了性能。
 *
 * 不过，这种方式的代码实现相对复杂，需要对多线程的内存模型和指令重排等知识有较好的理解，以确保正确实现。
 */
public class Demo3 {
    public static void main(String[] args) {
        SingletonLazy1 s1 = SingletonLazy1.getInstance();
        SingletonLazy1 s2 = SingletonLazy1.getInstance();
        System.out.println(s1 == s2);
    }
}
