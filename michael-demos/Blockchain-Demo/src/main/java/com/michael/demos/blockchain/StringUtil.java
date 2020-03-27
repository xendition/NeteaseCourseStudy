package com.michael.demos.blockchain;

import java.security.MessageDigest;

/**
 * 类功能描述:
 * <pre>
 *   签名算法
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/10/29 19:50
 */
public class StringUtil {

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
