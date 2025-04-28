package org.study.je20250428springaopdemo.proxy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 9:07
 */
public class RealHouseSubject implements HouseSubject {
    @Override
    public void saleHouse() {
        System.out.println("我是房东，我卖房子~");
    }

    @Override
    public void rentHouse() {
        System.out.println("我是房东，我出租房子~");
    }
}
