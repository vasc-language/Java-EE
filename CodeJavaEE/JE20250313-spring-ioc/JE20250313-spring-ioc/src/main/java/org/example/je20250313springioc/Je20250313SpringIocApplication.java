package org.example.je20250313springioc;

import org.example.je20250313springioc.controller.HelloController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 两类注释
 * 1. 类注释：@Controller、@Service、@Repository、@Component、@Configuration
 * 2. 方法注释：@Bean
 */
@SpringBootApplication
public class Je20250313SpringIocApplication {

    public static void main(String[] args) {
        // Spring 所需的运行环境
        ApplicationContext context = SpringApplication.run(Je20250313SpringIocApplication.class, args);
        // 常用的三种方式获取对象
        // getBean(String name) 缺点是需要强制类型转换
        // getBean(Class<> requiredType)
        // getBean(String name, Class<> requiredType) 没啥缺点
        HelloController bean1 = (HelloController) context.getBean("HelloController");
        bean1.print();
        HelloController bean2 = context.getBean(HelloController.class);
        bean2.print();
        HelloController bean3 = context.getBean("HelloController", HelloController.class);
        bean3.print();

        // org.example.je20250313springioc.controller.HelloController@12ad1b2a 三者都表示同一个对象
        System.out.println(bean1);
        System.out.println(bean2);
        System.out.println(bean3);

    }

}
