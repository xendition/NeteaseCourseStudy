package com.michael.demo.spring.event;

import com.michael.demo.spring.event.entity.Entity;
import com.michael.demo.spring.event.event.MyEventA;
import com.michael.demo.spring.event.event.MyEventB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/16 18:50
 */
@EnableAsync
@SpringBootApplication
public class EventTestApplication {


    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext application = SpringApplication.run(EventTestApplication.class, args);

        for (int i = 0; i < 16; i++) {
            application.publishEvent(new MyEventA(new Entity("aaaa"),i));
            application.publishEvent(new MyEventB(new Entity("bbbb"),i));

            Thread.sleep(1000L);
        }
    }
}
