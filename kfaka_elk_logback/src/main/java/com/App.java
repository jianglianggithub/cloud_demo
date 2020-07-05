package com;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication()

@RestController
public class App {
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/a")
    public Object test(){
        ListenableFuture<SendResult<String, Object>> send = kafkaTemplate.send("hello", "aaa");

        logger.info("这是一个msg 测试 elk");
        return "发送";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
