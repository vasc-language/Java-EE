package spring.trans.demo.springtransdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.trans.demo.springtransdemo.service.LogService;
import spring.trans.demo.springtransdemo.service.UserService;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 18:00
 */
@Slf4j
@RequestMapping("/user2")
@RestController
public class RollbackUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @RequestMapping("/registry")
    public String registry(String userName, String password) {
        /**
         * 业务流程
         * 1. 开启事务
         * 2. 数据操作
         * 3. 事务提交/回滚
         */
        // 1. 用户注册
        Integer result = userService.registryUser(userName, password);
        log.info("用户注册成功，影响行数：" + result);
        try {
            // 开始测试发生异常，事务是否回滚
            int a = 10 / 0;
        } catch (Exception e) {
            log.error("程序发生异常~");
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return "注册成功~";
    }

    /**
     * 事务并没有回滚
     * @param userName
     * @param password
     * @return
     * @throws IOException
     */
    @RequestMapping("/r2")
    public String r2(String userName, String password) throws IOException {
        // 用户注册
        Integer result = userService.registryUser(userName, password);
        log.info("用户注册成功，影响行数：" + result);
        if (true) {
            throw new IOException();
        }
        return "注册成功~";
    }

    /**
     * 事务发生回滚
     * 事务的隔离级别
     * - READ UNCOMMITTED（读未提交）隔离级别最低的。一个事务可以读取其他事务上未提交的修改
     *   可能出现的问题：
     *    - 脏读（Dirty Read）：读取到其他事务回滚的数据（但这些数据是无效的脏数据）
     *    - 不可重复读（Non-Repeatable Read）：在同一个事务中，两次读取到同一行数据，结果不同
     *    - 幻读（Phantom Read）：同一个事务内，两次执行同样的范围查询，结果集中的行数不同。
     * - READ COMMITTED（读已提交）一个事务只能读取其他事务已经提交的修改
     *   可能出现的问题：
     *    - 不可重复读（Non-Repeatable Read）：事务 A 读取某行数据后，事务 B 修改了改行并提交，事务 A 再次读取该行时，发现数据发现变化
     *    - 幻读（Phantom Read）：事务 A 读取某个范围的数据后，事务 B 在该范围内插入新行并提交，事务 A 再次读取该行时，发现多了“幽灵行”
     * - REPEATABLE READ（可重复读）保证在同一事务中，多次读取同一行数据的结果是一致的。事务开始会创建数据快照，后续的读取操作都基于这个快照
     *   可能出现的问题：
     *   - 读(Phantom Read):虽然读取同一行数据结果一致，但如果执行范围查询，其他事务在该范围内插入了新数据并提交，当前事务再次执行范围查询时
     *       仍可能看到这些新插入的行（在某些数据库实现中，如 MySQL InnoDB 通过 MVCC 和Next-Key Locking 解决了标准定义下的幻读问题，但
     *       在特定场景下仍需注意）
     * - SERIALIZABLE（可串行化）这是最高的隔离级别。强制事务串行执行，即一个事务完全执行完毕，另外的事务才能开始。通常在读取的每一行数据上都
     *     加上锁（通常是范围锁或表锁）来实现
     *     缺点：并发性能最差，因为事务需要排队执行，大大降低了数据库的吞吐量。在需要高并发的应用中很少使用
     * @param userName
     * @param password
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED) // read_committed
    @RequestMapping("/r3")
    public String r3(String userName, String password) {
        // 用户注册
        Integer result = userService.registryUser(userName, password);
        log.info("用户注册成功，影响行数为：" + result);
        try {
            if (true) {
                throw new IOException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "注册成功~";
    }

    @Transactional
    @RequestMapping("/r5")
    public String r5(String userName, String password) {
        // 用户注册 + 记录
        Integer result = userService.registryUser(userName, password);
        log.info("用户注册成功，影响行数：" + result);
        Integer logResult = logService.insertLog(userName, "用户注册");
        log.info("日志记录成功，影响行数为：" + logResult);

        return "注册成功~";
    }
}
