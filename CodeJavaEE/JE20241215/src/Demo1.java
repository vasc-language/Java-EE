/**
 * Created with IntelliJ IDEA.
 * Description: 通过饿汉模式构造单例模式
 * User: 姚东名
 * Date: 2024-12-15
 * Time: 14:40
 */

/**
 * 定义
 * 在 Java 多线程的单例模式实现中，饿汉模式（Eager Initialization）是一种创建单例对象的方式。
 * 它的特点是在类加载的时候就创建单例对象，而不管这个对象是否会被使用。
 */
class Singleton {
    // 私有静态成员变量，直接访问实例（很急迫）
    private static Singleton instance = new Singleton();
    // 私有构造函数，防止外部通过new创建实例
    private Singleton() {};
    // 公共静态方法，用于获取单例实例
    public static Singleton getInstance() {
        return instance;
    }
}
public class Demo1 {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true
    }
}
