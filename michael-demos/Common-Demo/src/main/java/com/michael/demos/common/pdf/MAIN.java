package com.michael.demos.common.pdf;

import java.util.List;

public class MAIN {


    public static void main(String[] args) throws InterruptedException {

        //for (int i = 0; i < 1000000; i++) {
        //    t();
        //}

        System.out.println(js(105445));

        //String pdfPath1 = "E://pdf_test/nepxion/Nepxion.pdf";
        //String pdfPath2 = "E://pdf_test/axure/axure.pdf";
        //
        //List<String> list = PDFUtils.pdfPathToImagePaths(pdfPath2);
        //for (int i = 0; i < list.size(); i++) {
        //    System.out.println("path==" + list.get(i));
        //}
    }

    public static int js(int h)  {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private volatile static int x = 0, y = 0;
    private volatile static int a = 0, b = 0;

    public static void t() throws InterruptedException {
        x = 0;y = 0;a = 0; b = 0;
        Thread t1 = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread t2 = new Thread(() -> {
            b = 1;
            y = a;
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("(" + x + "," + y + ")");
    }
}
