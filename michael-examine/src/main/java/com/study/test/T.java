package com.study.test;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/8/6 19:20
 */
public class T {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // for (int i = 0; i < 10; i++) {
        //     System.out.println(passwordEncoder.encode("123456"));
        // }

        Arrays.asList(
                "$2a$10$5m9IKEN9QgT8mq926hmI/e6AU/LzUnJGDD5q/7Jw3kWEseS.6tjWq",
                "$2a$10$Y7ZtaA8F4QwPmQWi/foH0utT6SpWHUOPL49oj3W55W353dzlp.vSW",
                "$2a$10$S.FhlTJNcrfP.koLsAxk7.XkDD2gVq/ejE1YS/qBumR6uhkzvH3uK",
                "$2a$10$zeygNLzbcMXFz7bdHVToQuXklxdOfUDwFLijco.eEAmQWzG0Esnfi",
                "$2a$10$t7BCWSK2pBttEaKUdcyZLeRtEMQXUnRaBIBqnH7tSVcsfEnEedaOC",
                "$2a$10$AOrzI.Xfp3pMHCO2NsxZue0Ury0NLMOUNcHw0HfOl402vsp6TS2bi",
                "$2a$10$99nxLEMxxNppqFOLvW969ux6BKCX0eB345sNJYZQrTgXqUL1g/Bxa",
                "$2a$10$EIOpwsztLYQ8p4GzV3nUouiGbuEr6gtx3ZBDpR1ZPOf.VeTVcZ122",
                "$2a$10$2Gl.LlLIrcU93JA3g/pTfusAmpMItkhQL4MLt8f650NM01pJyv816",
                "$2a$10$H8Nic0EbeZ4h5vr/JovyQ.3qQn6hLmMsByof6Lq4lowOqyzGlXyfe"
        ).forEach(item -> System.out.println(passwordEncoder.matches("123456", item)));


        Map<String, String> paramMap = new HashMap<>(16);
        paramMap.put("test", "123");
        paramMap.put("test1", "1233");
        paramMap.put("test2", "1234");
        paramMap.put("test3", "1235");


        System.out.println(new Gson().fromJson(new Gson().toJson(paramMap), Map.class).get("test3"));


        // Gson g = new Gson();
        // JsonObject obj = g.fromJson(msgStr, JsonObject.class);
        // System.out.println(obj.get("test"));
        // for (Map.Entry<String, JsonElement> set : obj.entrySet()) {//通过遍历获取key和value
        //     System.out.println(set.getKey() + "_" + set.getValue());
        // }

        // System.out.println(JSON.parseObject("{\"a\":\"\\x"));

        // System.out.println("".equals(null));
        //
        // System.out.println(new Integer(1) instanceof Number);
        //
        // System.out.println(LocalDate.now().toString());
        //
        // int result = 0;
        // for (int i = 50; i < 100; i+=2) {
        //     result += i;
        // }
        // System.out.println(result * 4);

        // System.out.println(System.currentTimeMillis());
        // // String format = MessageFormat.format("{0} abc {1}", 111112, new Date());
        // // System.out.println(format);
        // String format = String.format("%s abc %2$tF %2$tT", 111112, new Date());
        // System.out.println(format);
        // System.out.println(System.currentTimeMillis());

        // List list = null;
        // list.forEach(System.out::println);
        //
        // // System.out.println(null == null);
        //
        // // System.out.println(123);
        //
        // System.out.println(System.currentTimeMillis());
        // System.out.println();
        //
        // // System.out.println(new BigDecimal(1.00).setScale(2).compareTo(new BigDecimal(1)));
        // for (int i = 0; i < 100; i++) {
        //     System.out.println(random(16));
        // }
        //
        // System.out.println();
        // System.out.println(System.currentTimeMillis());


        // forEach();
        // map();
    }

    private static void forEach() {
        RANDOM.ints().limit(10).filter(i -> i > 0).forEach(System.out::println);
    }

    private static void map() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        // 获取对应的平方数
        numbers.stream().map(i -> i * i).distinct().forEach(System.out::println);
        numbers.stream().map(i -> i * i).sorted().forEach(System.out::println);
    }

    /** 安全的随机 */
    private static final Random RANDOM = new SecureRandom();

    /** 生成一个 size 位长度的编号 */
    private static String random(int size) {
        StringBuilder randomCode = new StringBuilder();
        final String model = "01234abcdefgABCDEFGhijklmnHIJKLMNopqrstOPQRSTuvwxyzUVWXYZ56789";
        for (int j = 0; j < size; j++) {
            randomCode.append(model.charAt(RANDOM.nextInt(model.length())));
        }
        return randomCode.toString();
    }
}
