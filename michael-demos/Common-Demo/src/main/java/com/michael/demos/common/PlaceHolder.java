package com.michael.demos.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/12/19 14:46
 */
public class PlaceHolder {

    public static String renderString(String str, Map<String,String> map) {

        Set<Map.Entry<String, String>> sets = map.entrySet();
        for (Map.Entry<String, String> entry : sets) {
            String regex = "\\$\\{" + entry.getKey() + "\\}";
            str = Pattern.compile(regex).matcher(str).replaceAll(entry.getValue());
        }
        return str;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>(8);
        map.put("a", "AA");
        map.put("aaa", "AA");
        map.put("bbb", "BB");
        map.put("ccc", "CC");
        String a = "a${aaa}111${bbb}222${ccc}333";
        System.out.println(renderString(a, map));
    }
}
