package com.yibu;

import java.util.concurrent.CompletableFuture;

public class BBB {


    public static void main(String[] args) {


        test(new String[]{"", "", ""});
    }


    public static void test(String... a) {
        System.out.println(a.length);
    }
}
