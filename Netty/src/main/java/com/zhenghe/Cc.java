package com.zhenghe;

import com.alibaba.fastjson.JSON;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class Cc {

    public static void main(String[] args) {
        LocalDateTime s = LocalDateTime.of(2020, 3, 3, 12, 5, 30);
        LocalDateTime e = LocalDateTime.of(2020, 3, 3, 12, 30, 1);
        long hours = Duration.between(e, s).toMinutes();
        System.out.println(hours);

    }
}
