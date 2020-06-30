package com.michael.demos.common.file;

import java.io.*;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/5/15 11:05
 */
public class FilesTest {

    public static void demo(String filePath) throws IOException {

        System.out.println("当前上传文件为：" + filePath);
        File file = new File(filePath);
        System.out.println("文件大小：" + file.length());
        FileInputStream fileInputStream = new FileInputStream(file);

        ByteArrayOutputStream temp = Files.cloneInputStream(fileInputStream);
        // 获取文件类型
        InputStream s1 = new ByteArrayInputStream(temp.toByteArray());
        // 获取文件MD5
        InputStream s2 = new ByteArrayInputStream(temp.toByteArray());
        // 文件上传
        InputStream s3 = new ByteArrayInputStream(temp.toByteArray());

        String fileType = Files.getFileType(s1);
        System.out.println("文件类型：" + fileType);
        String md5 = Files.getMd5(s2);
        System.out.println("文件MD5：" + md5);

        int hashCode = Math.abs(md5.hashCode());

        String targetFilePath = "D:/" + fileType + "/" + (hashCode % 64) + "/" + md5 + "." + fileType;

        System.out.println("文件所在目录为：" + targetFilePath);

        System.out.println("开始文件复制...");

        File dest = new File(targetFilePath);
        if (!dest.exists()){
            dest.getParentFile().mkdirs();
        }
        OutputStream output = null;

        try {
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = s3.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } finally {
            s3.close();
            if (output != null) {
                output.close();
            }
        }

        System.out.println("文件复制完成");
    }

    public static void main(String[] args) throws IOException {

        /**
         *  1.获取文件MD5值
         *  2.文件改名 -> MD5值同名
         *  3.文件取模 -> 得到文件最后的目录地址
         *  4.不同的文件类型对应的父目录不同
         */

        String filePath = "F:\\OSS备份\\scale-prod\\image\\special\\goods\\2020-05-05\\31c3f74c-e670-4580-8f88" +
                          "-517287f84f85.png";
        filePath = "F:\\OSS备份\\scale-test\\file\\excel\\reconciliation\\9e2114c1-292d-4b5a-8cd7-285ed44dac3e.xls";

        demo(filePath);
        //
        // ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(filePath.getBytes());
        // String fileType = Files.getFileType(byteArrayInputStream);
        // System.out.println(fileType);
        //
        // String md5 = Files.getMd5(byteArrayInputStream);
        // System.out.println(md5);
        //
        // FileInputStream fileInputStream = new FileInputStream(filePath);
        // fileType = Files.getFileType(fileInputStream);
        // System.out.println(fileType);
        //
        // md5 = Files.getMd5(byteArrayInputStream);
        // System.out.println(md5);
        // System.out.println(md5.hashCode()%64);


        // String md5 = Files.getMd5(filePath);
        // System.out.println(md5);
        //
        // System.out.println("D00B779B54198BE5AD6535D00EB315EA".toLowerCase());
        //
        // String strMd5 = Files.getStrMd5(md5);
        // System.out.println(strMd5);
        //
        // strMd5 = Files.getStrMd5("1");
        // System.out.println(strMd5);
    }
}
