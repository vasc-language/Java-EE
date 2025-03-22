package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-15
 * Time: 18:03
 */
// 饿汉式
class SingletonHungry {
    private static  final Singleton instance = new Singleton();

    //构造方法：防止 new 一个对象实例化对象
    public SingletonHungry() {}

    //获取到一个Singleton
    public static Singleton getInstance() {
        return instance;
    }
}

// 懒汉式
class Singleton {
    private static Singleton instance = null;

    public Singleton() {}
    //当 instance 为 null 时才进行实例化

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// 线程安全的懒汉式
class SingletonLazy1 {
    private static SingletonLazy1 instance = null;

    public SingletonLazy1() {}

    public static synchronized SingletonLazy1 getInstance() {
        if (instance == null) {
            instance = new SingletonLazy1();
        }
        return instance;
    }
}

// 双重检验锁
class SingletonLazy {
    private static volatile SingletonLazy instance = null;

    public SingletonLazy() {}

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

//  静态内部类实现
class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {}

    private static class Holder {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return Holder.instance;
    }
}

// 枚举单例
enum EnumSingleton {
    INSTANCE;

    public void someMethod() {
        // 方法实现
    }
}


public class Demo8 {
    public static void main(String[] args) {

    }
}
