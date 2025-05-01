package spring.trans.demo.springtransdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import spring.trans.demo.springtransdemo.mapper.LogInfoMapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 姚东名
 * Date: 2025-05-01
 * Time: 18:36
 */
@Slf4j
@Service
public class LogService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertLog(String userName, String op) {
        // 记录用户操作
        try {
            Integer result = logInfoMapper.insertLog(userName, op);
            // int i = 10 / 0;
            return result;
        } catch (Exception e) {
            log.error("事务回滚~");
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return null;
    }
}
