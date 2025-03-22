package two.test;

class A {
    synchronized void methodA(B b) {
        System.out.println("Thread 1: Locking A and calling B");
        b.last();
    }

    synchronized void last() {
        System.out.println("In A's last method");
    }
}

class B {
    synchronized void methodB(A a) {
        System.out.println("Thread 2: Locking B and calling A");
        a.last();
    }

    synchronized void last() {
        System.out.println("In B's last method");
    }
}

public class DeadlockExample {
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();

        Thread t1 = new Thread(() -> a.methodA(b));
        Thread t2 = new Thread(() -> b.methodB(a));

        t1.start();
        t2.start();
    }
}
