/**
 * Created with IntelliJ IDEA.
 * Description: 通过懒汉模式构造单例模式
 * User: 姚东名
 * Date: 2024-12-15
 * Time: 15:03
 */

/**
 * 定义：
 * 在 Java 多线程环境下，懒汉模式（Lazy Initialization）是一种用于创建单例对象的设计模式。
 * 它的核心思想是延迟对象的创建，即只有在第一次需要使用这个单例对象时才进行创建，
 * 而不是像饿汉模式那样在类加载阶段就创建好。这就好比一个人很 “懒”，
 * 不到万不得已（真正需要使用这个对象的时候），不会去创建这个对象。
 */
class SingletonLazy {
    // 私有静态变量，用于存储单例的对象，初始值为null
    private static SingletonLazy instance = null;
    // 私有构造方法，防止外部通过new 关键字创建实例
    private SingletonLazy() {};
    // 公共静态方法，用于获取单例模式
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
public class Demo2 {
    public static void main(String[] args) {
        SingletonLazy s1 = SingletonLazy.getInstance();
        SingletonLazy s2 = SingletonLazy.getInstance();
        System.out.println(s1 == s2); // true
    }
}
