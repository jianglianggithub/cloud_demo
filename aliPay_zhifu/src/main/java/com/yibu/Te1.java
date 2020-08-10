package com.yibu;

import java.util.concurrent.*;

public class Te1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("222222");
            return 1;
        });

        boolean done = f1.isDone();
        f1.complete(2);
        Integer integer = f1.get();
        System.out.println(done);
        System.out.println(integer);

//        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.supplyAsync(() -> {
//            throw new RuntimeException("1");
//        },null);
//        CompletableFuture<Object> exceptionally = objectCompletableFuture.exceptionally((e) -> {
//            return 1;
//        });
//        boolean done1 = exceptionally.isDone();
//        System.out.println(done1);


        System.out.println("=====================================");
        CompletableFuture<Object> f12 = new CompletableFuture<>();
        f12.complete(1);
        CompletableFuture<Integer> integerCompletableFuture = f12.handleAsync((e, a) -> {
                    System.out.println(Thread.currentThread().getName());
                    return 1;
                }
                , Executors.newFixedThreadPool(1, new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"线程池"+(int)Math.random());
                    }
                }));
        
        integerCompletableFuture.get();
    }
}
