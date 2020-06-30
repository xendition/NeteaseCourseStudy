package com.michael.demos.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/4/26 17:45
 */
public class Md5Test {

    public static Integer getHash(String source) {

        return source.hashCode();
        // return SecureUtil.md5().setSalt("12kedu".getBytes()).setDigestCount(3).digestHex(source, "UTF-8");
    }

    public static void main(String[] args) {
        // String source = "insurance:insureEpairType:add";

        // Arrays.asList("insurance:insureEpairType:add", "insurance:insureEpairType:edit",
        //               "insurance:insureEpairType:delete"
        // ).forEach(System.out::println);
        // Arrays.asList(
        //         "insurance:insureEpairType:add",
        //         "insurance:insureEpairType:edit",
        //         "insurance:insureEpairType:delete"
        // ).forEach(item -> System.out.println(getHash(item)));

        // System.out.println(getHash(source));

        password();
    }

    private static String source = "0123456789abcdefghijklmnopkrstuvwxyz";

    private static void password() {


        Arrays.asList(
                "李奕航", "林月卿", "梁智渊", "刘秦", "倪银鹏", "钟幸圆", "丁琴星", "巫晓杰", "张文凯", "李湛", "谢毓琦", "阳丽萍", "何斌", "李智",
                "王景立", "温泉", "曾冬令", "覃俊榜", "林启升", "尹哲", "黄锦涛", "谢子颖", "叶澴进", "高士全", "招俊辉", "黄楚忠", "彭贤良", "蒲焕琳",
                "欧阳璐", "梁芷晴", "李佳莲", "邵子凌", "周萱", "陈坚", "唐文乐", "陈剑明", "梁飞洲", "罗绮文"
        ).forEach(item -> {
            String password = RandomStringUtils.random(6, source);

            System.out.println(item + "\t" + password);
        });
    }
}
