package com;

import com.rabbit.IReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableBinding({IReceiverService.class})//开启这个注解后 会找到 这个接口实例化 并检测 StreamListener
public class App extends SpringBootServletInitializer {

    @Autowired
    private IReceiverService iReceiverService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(getClass());
    }

    @RequestMapping("/test")
    public String test(String key) {
        System.out.println("112312313");
        Message<String> build = MessageBuilder.withPayload("{\"aaa\":\"123\"}").setHeader("partitionKey", key).build();

        boolean send = iReceiverService.send().send(build);
        return send+"";
    }
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
