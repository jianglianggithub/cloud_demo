package com;


import com.entity.Dept;
import com.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.entity"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    public static void aa(Dept dept) {
        System.out.println(dept);
    }

    public static void bb(User user) {
        System.out.println(user);
    }
}
