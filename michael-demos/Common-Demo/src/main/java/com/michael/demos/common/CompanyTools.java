package com.michael.demos.common;

import org.apache.commons.lang3.RandomStringUtils;
import org.nlpcn.commons.lang.pinyin.Pinyin;

import java.util.Arrays;
import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/4/30 15:14
 */
public class CompanyTools {

    public static void main(String[] args) {
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
            System.out.println(item + "\t" + getPinyin(item)+ "\t" + password);
        });
    }

    private static String getPinyin(String name) {

        List<String> pinyin = Pinyin.pinyin(name);
        String result = "";
        for (String p : pinyin) {
            result += p;
        }
        return result;
    }
}
