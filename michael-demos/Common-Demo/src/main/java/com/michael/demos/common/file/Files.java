package com.michael.demos.common.file;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/5/15 10:58
 */
public class Files {

    /** 获取字符串的MD5值 */
    public static String getStrMd5(String data) {
        return DigestUtils.md5Hex(data);
    }

    /** 根据文件路径获取文件MD5值 */
    public static String getMd5(String filePath) throws IOException {
        return getMd5(new File(filePath));
    }

    /** 根据流获取文件MD5值 */
    public static String getMd5(InputStream inputStream) throws IOException {
        return DigestUtils.md5Hex(inputStream);
    }

    /** 根据文件流获取文件类型 */
    public static String getFileType(InputStream inputStream) throws IOException {
        String fileHead = getFileHead(inputStream);
        System.out.println("fileHead:" + fileHead);
        return FileType.get(fileHead);
    }

    /**
     * InputStream转ByteArrayOutputStream，用于流的复制
     */
    public static ByteArrayOutputStream cloneInputStream(InputStream input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = input.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return baos;
    }

    /**
     * 得到文件头
     */
    private static String getFileHead(InputStream inputStream) throws IOException {
        byte[] b = new byte[28];
        try {
            inputStream.read(b, 0, 28);
        } finally {
            inputStream.close();
        }
        return bytesToHexString(b);
    }

    /**
     * 将文件头转换成16进制字符串
     */
    private static String bytesToHexString(byte[] bytes){

        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (byte b : bytes) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /** 根据文件获取文件MD5值 */
    public static String getMd5(File file) throws IOException {
        return DigestUtils.md5Hex(new FileInputStream(file));
    }

    /** 根据字节数组获取文件MD5值 */
    public static String getMd5(byte[] bytes) {
        return DigestUtils.md5Hex(bytes);
    }
}
