package spring.trans.demo.springtransdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.trans.demo.springtransdemo.mapper.UserInfoMapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 17:44
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 用户注册

    /**
     * @Transactional 是 Spring 框架中用于声明式事务管理的核心注解，主要作用是告诉 Spring 容器，某个方法（或类中所有的公共方法）需要在上下文
     *   执行，Spring 会自动为你处理事物的开启，提交或回滚
     *   - propagation：事务传播行为（REQUIRED, REQUIRES_NEW, NESTED等）定义了当前方法如何加入或创建事务（NESTED 嵌套的）
     *   - isolation：事务隔离级别（READ_COMMITTED, SERIALIZABLE）控制并发的可见性（SERIALIZABLE 可序列化的）
     *   - readOnly：是否为只读事务，可以进行一些数据库层面的优化
     *   - timeout：事务的超时时间（second）
     *   - rollbackFor/rollbackForClassName：指定哪些异常类型不触发事务回滚
     *   - noRollbackFor/noRollbackForClassName：指定哪些异常类型不触发事务回滚
     *   - transactionManager / value：指定使用哪个事务管理器（当有多个事务管理器）
     *
     * 工作原理
     * Spring 通过 APO（Aspect-Oriented-Programming）代理来完成实现@Transactional 的功能，当你调用一个标记了 @Transactional 的 bean
     * 1. Spring 的事务拦截器（Transaction Interception） 会介入
     * 2. 拦截器根据注解的配置（传播机制、隔离级别等）决定是开启一个事务，还是加入一个已有的事务
     * 3. 执行目标方法（业务逻辑）
     * 4. 如果方法执行成功，拦截器会提交事务
     * 5. 如果方法抛出异常，拦截器会根据 rollbackFor / noRollbackFor 的规则决定是回滚事务还是提交事务（默认情况下，运行时异常 RunTimeException
     *      和 Error 会触发回滚，受检查异常 CheckedException 不会）
     * @param userName
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED) // required
    public Integer registryUser(String userName, String password) {
        // 插入用户信息
        return userInfoMapper.insert(userName, password);
    }
}
