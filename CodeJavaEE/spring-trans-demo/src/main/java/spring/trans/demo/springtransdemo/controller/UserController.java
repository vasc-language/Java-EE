package spring.trans.demo.springtransdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.trans.demo.springtransdemo.service.UserService;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 18:00
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    /**
     * 1. dataSourceTransactionManager.get
     */
    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition definition;

    // @Transactional 这注解相当于把下面业务流程全都实现了
    @RequestMapping("/registry")
    public String registry(String userName, String password) {
        /**
         * 业务流程
         * 1. 开启事务
         * 2. 数据操作
         * 3. 事务提交/回滚
         */
        // 1. 开启事务
        // 根据事务定义（definition）获取一个事务状态 transactionStatus ,这标志事务的开始
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(definition);

        // 2. 数据操作：用户注册
        // 执行核心业务逻辑（用户注册）
        userService.registryUser(userName, password);

        // 3. 事务提交
        // 手动回滚事务
        // dataSourceTransactionManager.commit(transactionStatus);
        // 事务回滚
        dataSourceTransactionManager.rollback(transactionStatus);

        return "注册成功~";
    }
}
