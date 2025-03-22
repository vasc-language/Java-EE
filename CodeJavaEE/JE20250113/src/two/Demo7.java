package two;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-01-14
 * Time: 18:10
 */
class A {
    synchronized void methodB(B b) {
        System.out.println("Thread 1: Locking A and calling B"); // 锁定 A 并呼叫 B
        b.last();
    }
    synchronized void last() {
        System.out.println("In A's last method");
    }
}
class B {
    synchronized void methodA(A a) {
        System.out.println("Thread 2: Locking B and calling A"); // 锁定 B 并呼叫 A
        a.last();
    }
    synchronized void last() {
        System.out.println("In B's last method");
    }
}
public class Demo7 {
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();

        Thread t1 = new Thread(() -> {
            a.methodB(b);
        });
        Thread t2 = new Thread(() -> {
            b.methodA(a);
        });

        t1.start();
        t2.start();
    }
}
