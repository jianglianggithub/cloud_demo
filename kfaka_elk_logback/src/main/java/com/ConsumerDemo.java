package com;

import org.apache.kafka.clients.consumer.ConsumerRecord;
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
}
