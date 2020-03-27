package com.michael.study.base.arithmetic;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/11/5 19:20
 */
public class AESUtils {
    // 解密密钥(自行随机生成)
    // 密钥key
    public static final String KEY = "qxhzngy266a186ke";
    // 向量iv
    public static final String IV = "1ci5crnda6ojzgtr";

    // 认证密钥(自行随机生成)
    // AccessKey
    public static final String AK = "s2ip9g3y3bjr5zz7ws6kjgx3ysr82zzw";
    // SecretKey
    public static final String SK = "uv8zr0uen7aim8m7umcuooqzdv8cbvtf";

    public static SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");
    public static Cipher cipher;

    static {
        try {
            //算法/模式/补码方式
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    //加密
    public static String Encrypt(String content) throws Exception {
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(IV.getBytes()));
        return new BASE64Encoder().encode(cipher.doFinal(content.getBytes()));
    }

    //解密
    public static String Decrypt(String content) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(IV.getBytes()));
            byte[] encrypted = new BASE64Decoder().decodeBuffer(content);
            try {
                byte[] original = cipher.doFinal(encrypted);
                return new String(original);
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    //获取认证签名(身份认证需要)
    public static String getSign(String currentTime) throws Exception {
        String sign = "";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ak", AK);
        map.put("sk", SK);
        map.put("ts", currentTime);
        //获取 参数字典排序后字符串
        String decrypt = getOrderMap(map);
        try {
            //指定sha1算法
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            //获取字节数组
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为十六进制数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            sign = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sign;
    }

    //获取参数的字典排序
    private static String getOrderMap(Map<String, Object> maps) {
        List<String> paramNames = new ArrayList<>();
        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            paramNames.add(entry.getValue().toString());
        }
        Collections.sort(paramNames);
        StringBuilder paramStr = new StringBuilder();
        for (String paramName : paramNames) {
            paramStr.append(paramName);
        }
        return paramStr.toString();
    }

    public static void main(String[] args) throws Exception {
        String content = "需要加密的内容";
        //加密
        String ens = AESUtils.Encrypt(content);
        System.out.println("加密后：" + ens);
        //解密
        String des = AESUtils.Decrypt(ens);
        System.out.println("解密后：" + des);
    }
}
