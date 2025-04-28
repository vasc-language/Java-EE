package org.study.je20250428springaopdemo.proxy;

/**
 * Created with IntelliJ IDEA.
 * Description: 静态的 代理方式
 * User: 姚东名
 * Date: 2025-04-28
 * Time: 9:13
 */
public class Main {
    public static void main(String[] args) {
        HouseSubject target = new RealHouseSubject(); // 真实的房东
        HouseSubject houseSubject = new HouseProxy(target); // 中介代理

        houseSubject.saleHouse(); // 代理卖房子
        houseSubject.rentHouse(); // 代理出租房子
    }

}
