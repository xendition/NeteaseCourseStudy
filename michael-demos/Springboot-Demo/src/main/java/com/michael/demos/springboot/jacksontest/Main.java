package com.michael.demos.springboot.jacksontest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/4/10 11:25
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        P p = new P().setAccount("abc").setId(1).setChildren(
                Arrays.asList(
                        new C().setAge(10).setName("A"),
                        new C().setAge(11).setName("B"),
                        new C().setAge(12).setName("C")
                )
        );

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(p);

        System.out.println(result);

    }
}
