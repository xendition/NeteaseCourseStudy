package com.michael.demos.elasticsearch;

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
 * @date 2019/05/25 16:20
 */

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("START SUCCESS");
    }
}
