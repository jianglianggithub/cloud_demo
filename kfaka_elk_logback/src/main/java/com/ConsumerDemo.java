package com;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDemo {

    @KafkaListener(topics = "hello")
    public void listen (ConsumerRecord<?, ?> record){
        System.out.printf("消费 topic is %s, offset is %d, value is %s \n",
                record.topic(),
                record.offset(),
                record.value()
        );
    }


    public static void main(String[] args) {

    }
}
