package com.michael.study.io;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.*;

/**
 * 类功能描述:
 * <pre>
 *   Io 常量类
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 08:46
 */
public interface IoConstant {

    Charset charset = Charset.forName("UTF-8");
    CharsetDecoder decoder = charset.newDecoder();

    ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2000, 2000,
                                                           0L, TimeUnit.MILLISECONDS,
                                                           new LinkedBlockingQueue<>(10000),
                                                           new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    String host = "127.0.0.1";
    int port = 11111;

}
