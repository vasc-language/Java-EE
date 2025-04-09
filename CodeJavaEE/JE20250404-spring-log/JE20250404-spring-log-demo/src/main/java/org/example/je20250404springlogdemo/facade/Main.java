package org.example.je20250404springlogdemo.facade;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:47
 */

/**
 * 门面模式是设计模式的一种（23种）
 * 设计模式重思想，轻代码实现
 */
public class Main {
    public static void main(String[] args) {
        FacadeClient facadeClient = new FacadeClient();
        facadeClient.on();
        System.out.println("==========");
        facadeClient.off();
    }

}
