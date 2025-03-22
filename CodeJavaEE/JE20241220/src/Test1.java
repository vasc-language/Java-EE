/**
 * Created with IntelliJ IDEA.
 * Description: 懒汉模式
 * User: 姚东名
 * Date: 2024-12-20
 * Time: 17:43
 */

/**
 * 1）类的定义及其成员变量声明
 * 1. private volatile static LazySingleton instance;
 * 声明了一个私有的、静态的、被volatile修饰的变量instance，这个变量用于存储单例模式下该类唯一的实例对象。
 * volatile关键字在这里非常关键，它保证了变量在多线程环境下的 “可见性”，也就是当一个线程修改了这个变量的值，其他线程能够立即看到最新的值，
 * 避免了因为指令重排序等原因导致的线程安全问题（后续在对象创建过程中会进一步体现其作用）。
 *
 * 2. private LazySingleton() {};
 * 定义了一个私有的构造函数，这意味着外部无法通过new关键字来随意创建LazySingleton类的实例，
 * 只能通过类内部提供的获取实例的方法来获取，这是保证单例模式实现的重要基础，限制了实例化的途径。
 *
 * 2）获取单例实例的方法
 * 1. 外层if判断：
 * if (instance == null)首先进行一次检查，判断单例对象是否已经被创建。
 * 如果已经创建（instance不为null），那就直接返回这个已经存在的实例，无需再进行后续的创建和同步操作，
 * 这样可以避免每次获取实例都进入同步块（因为获取同步锁有一定性能开销），提高了获取实例的效率，特别是在单例对象已经初始化完成后的多次调用场景中。
 *
 * 2. 同步块（synchronized块）：
 * 当外层if判断发现instance为null时，说明单例对象还未创建，此时进入synchronized (LazySingleton.class)同步块。
 * 这里使用类对象（LazySingleton.class）作为锁对象，意味着同一时刻只有一个线程能够进入这个同步块去创建单例对象，
 * 保证了创建过程的线程安全性，防止多个线程同时创建多个实例的情况发生。
 *
 * 3. 内层if判断：
 * 在同步块内部，又有一次if (instance == null)判断。
 * 这是因为当多个线程同时发现外层if判断中的instance为null时，它们会竞争进入同步块，
 * 而第一个进入同步块的线程会创建单例对象，后续进入同步块的线程如果没有这个内层if判断，就会又一次创建新的实例，违背了单例模式只能有一个实例的原则。
 * 内层if判断确保了即使多个线程进入了同步块，也只有一个线程会真正执行创建实例（instance = new LazySingleton();）的操作，保证了单例模式的正确实现。
 */
class SingletonLazy {
    // 声明了一个私有的、静态的、被volatile修饰的变量instance，这个变量用于存储单例模式下该类唯一的实例对象。
    private static volatile SingletonLazy instance = null;
    // 确保外界不能通过new 生成一个实例
    private SingletonLazy() {};
    // 外界只能通过getInstance获得instance
    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}
public class Test1 {
    public static void main(String[] args) {
        SingletonLazy instance1 = SingletonLazy.getInstance();
        SingletonLazy instance2 = SingletonLazy.getInstance();
        System.out.println(instance1 == instance2); // true
    }
}
