package com.michael.demo.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/28 21:38
 */
@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(SecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("SUCCESS");
    }
}
