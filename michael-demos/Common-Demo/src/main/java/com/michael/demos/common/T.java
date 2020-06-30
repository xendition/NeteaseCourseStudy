package com.michael.demos.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/10/14 11:39
 */
public class T {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.of(2020,10,1,0,0,0);
        LocalDateTime localDateTime2 = LocalDateTime.of(2020,4,1,0,0,0);
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("GMT+8"));

        System.out.println(now1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(now2.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(now1.isBefore(localDateTime));
        System.out.println(now1.isBefore(localDateTime2));

        for (int i = 0; i < 10; i++) {

            // String random = RandomStringUtils.random(6);
            // System.out.println("RandomStringUtils.random(6) -> " + random);

            // String random = RandomStringUtils.randomAscii(6);
            // System.out.println("RandomStringUtils.randomAscii(6) -> " + random);
            //
            // random = RandomStringUtils.random(6,"0123456789abcdefghijklmnopkrstuvwxyz");
            // System.out.println("RandomStringUtils.random(6,...) -> " + random);
        }

        // List<Long> firstList = new ArrayList<>();
        // List<Long> secondList = new ArrayList<>();
        //
        // for (long i = 0; i < 1000000; i++) {
        //     firstList.add(i);
        //     secondList.add(i + ThreadLocalRandom.current().nextInt(10000));
        // }
        //
        // List<Long> longs = fetchDiffList(firstList,secondList);
        //
        // longs.forEach(System.out::println);
    }


    /** 获取差集数据集合(first中有second中没有) */
    private static List<Long> fetchDiffList(List<Long> firstList, List<Long> secondList) {
        LinkedList<Long> result = new LinkedList<>(firstList);
        HashSet<Long> othHash = new HashSet<>(secondList);
        result.removeIf(othHash::contains);

        return result;
    }


    public static void test2() {

        int n = 5;
        while (n-- != 0) {
            System.out.println(n);
        }
    }

    /** final */
    public static void test1() {

        String a1 = "a";
        String b1 = "b";
        final String a2 = "a";
        final String b2 = "b";

        System.out.println(a1 + b1 == "a" + "b");
        System.out.println(a1 + b1 == "ab");

        System.out.println(a2 + b2 == "a" + "b");
        System.out.println(a2 + b2 == "ab");
    }

}
