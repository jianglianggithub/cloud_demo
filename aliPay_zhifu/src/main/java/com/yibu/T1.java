package com.yibu;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class T1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> exceptionally = CompletableFuture.supplyAsync(() -> {
            return "1";
        }).exceptionally(throwable -> {
            return "2";
        });

        String s = exceptionally.get();
        System.out.println(s);

    }
}
