package com.yibu;

import org.assertj.core.util.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 1;
        });
//
//        CompletableFuture<Integer> future1 = future.thenCombineAsync(CompletableFuture.supplyAsync(() -> {
//            System.out.println(Thread.currentThread().getName());
//
//            return 1;
//        }), (a, b) -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(2);
//
//            return a + b;
//        });
//        System.out.println(1);
//        Integer integer = future1.get();
        CompletableFuture<Void> allDone = CompletableFuture.allOf(future); // <8>
        CompletableFuture<Integer> future2 = allDone.thenApply(t -> {
            System.out.println(t);
            return 1;
        });

    }
}