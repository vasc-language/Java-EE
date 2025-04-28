package org.study.je20250428springaopdemo.proxy;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 9:09
 */
public class HouseProxy implements HouseSubject {
    private HouseSubject realHouseSubject;

    public HouseProxy(HouseSubject realHouseSubject) {
        this.realHouseSubject = realHouseSubject;
    }
    @Override
    public void saleHouse() {
        System.out.println("我是中介，我帮房东代理卖房~");
    }

    @Override
    public void rentHouse() {
        System.out.println("我是中介，我帮房东代理租房~");
    }
}
