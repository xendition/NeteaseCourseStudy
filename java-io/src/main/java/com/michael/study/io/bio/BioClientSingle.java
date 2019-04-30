package com.michael.study.io.bio;

import com.michael.study.io.IoConstant;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 类功能描述:
 * <pre>
 *   BIO - 客户端 - 启动一个
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 08:44
 */
public class BioClientSingle implements IoConstant {

    public static void main(String[] args) {

        try (
                Socket socket = new Socket(host, port);
                OutputStream out = socket.getOutputStream()
        ) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入：");
            String msg = scanner.nextLine();

            // 阻塞，写完成
            out.write(msg.getBytes(charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
