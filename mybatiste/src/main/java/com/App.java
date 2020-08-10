package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.mapper"})
public class App {
    public static void main(String[] args) {

        SpringApplication.run(App.class);
    }
}
