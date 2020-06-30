package com.michael.study.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/3/27 9:44
 */
public class FileDemo {


    private static List<Integer> ERR_LINES = new ArrayList<>();

    public static void main(String[] args) {
        String filePath = "D:\\12kedu\\生产环境部署\\1.0数据同步\\待导入数据.txt";

        readFileByLines(filePath);

        if (ERR_LINES.size() > 0) {
            System.out.println("有误的数据行：");
            ERR_LINES.forEach(System.out::print);
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName 文件名
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            System.out.println("文件大小 -> " + file.length());
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            System.out.println();
            String tempString = null;
            int line = 1;
            //一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {

                // 处理业务
                doSomething(line, tempString);
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务处理
     *
     * @param line    行号
     * @param lineStr 读取的一行的数据
     */
    public static void doSomething(int line, String lineStr) {

        //显示行号
        System.out.println("line " + line + ": " + lineStr);

        String[] arr = lineStr.split("\t");
        int length = arr.length;
        // System.out.println(length);

        if (length == 3){
            String name = arr[0];
            String gender = arr[1];
            String phone = arr[2];

            System.out.println("姓名:" + name);
            System.out.println("性别:" + gender);
            System.out.println("账号:" + phone);

            if ("".equals(phone) || "".equals(phone.trim())) {
                ERR_LINES.add(line);
            }
        } else {
            ERR_LINES.add(line);
        }

        System.out.println();
    }
}
