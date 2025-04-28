package org.study.je20250428springaopdemo.proxy;

/**
 * Created with IntelliJ IDEA.
 * Description: 静态的 代理方式
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 9:09
 */

/**
 * 静态代理是一种设计模式，它允许你为其他对象提供一个代理或占位符，以控制对这个对象的访问。代理对象在客户端
 * 和目的对象之间充当中介，可以在调用目标方法前后添加额外的逻辑（如日志记录、权限检查、事务管理），而无需修
 * 改目标对象的代码
 * 在静态代理模式中，代理类和目标类通常会实现同一个接口或继承同一个父类。代理类在编译时就已经创建好了，因此
 * 被称为“静态代理”
 *
 */
public class HouseProxy implements HouseSubject {
    private HouseSubject realHouseSubject;

    public HouseProxy(HouseSubject realHouseSubject) {
        this.realHouseSubject = realHouseSubject;
    }
    @Override
    public void saleHouse() {
        System.out.println("我是中介，我帮房东卖房代理开始~");
        realHouseSubject.saleHouse();
        System.out.println("我是中介，我帮房东卖房代理结束~");
    }

    @Override
    public void rentHouse() {
        System.out.println("我是中介，我帮房东租房代理开始~");
        realHouseSubject.rentHouse();
        System.out.println("我是中介，我帮房东租房代理结束~");
    }
}
