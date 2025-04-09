package org.example.je20250404springlogdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description: 日志级别
 * User: 姚东名
 * Date: 2025-04-04
 * Time: 11:20
 */
@RequestMapping("/log")
@RestController
public class LogController {
    // 一共四种：
    // java.util.logging
    // org.slf4j（现在使用这个）
    // ch.qos.logback.classic
    // org.apache.logging.log4j
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    /**
     * 日志的级别从高到低依次为：FATAL、ERROR、WARN、INFO、DEBUG、TRACE
     * FATAL：致命信息，表示需要立即处理
     * ERROR：错误信息，级别较高，但仍然不影响系统的正常运行
     * WARN：警告信息，不影响使用，但需要注意的问题
     * INFO：普通信息，用于记录应用程序正常运行的一些信息，例如系统自启动、请求处理完成
     * DEBUG：调试信息，需要调试时候的关键信息打印.
     * TRACE：追踪信息，比 DEBUG 更细粒度的信息事件（除非有特殊用意，否则请使用 DEBUG 级别替代）
     */
    @RequestMapping("/print")
    public String print() {
        System.out.println("sout 打印日志");
        logger.info("logger 打印日志");

        logger.trace("logger trace~");
        logger.trace("logger debug~");
        logger.trace("logger warn~");
        logger.trace("logger info~");
        logger.trace("logger error~");
        logger.trace("logger fatal~");

        return "打印日志级别";
    }

    /**
     * 2025-04-04T11:27:34.397+08:00  INFO 2804 --- [JE20250404-spring-log-demo] [nio-8080-exec-2] o.e.j.controller.LogController           : logger 打印日志
     * 执行后，打印在控制台中
     * 2025-04-04T11:27:34.397+08:00：时间
     * INFO：日志级别
     * 2804：进程ID
     * JE20250404-spring-log-demo：应用名称（在配置文件中有）
     * nio-8080-exec-2：线程名称
     * o.e.j.controller.LogController：类的路径（package org.example.je20250404springlogdemo.controller的缩写）
     * logger 打印日志：日志内容
     */
}
