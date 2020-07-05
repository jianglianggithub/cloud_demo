package com.rabbit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface IReceiverService {


    String INPUT = "myInput";
    String OUTPUT = "myOutput";
    /**
     * 指定输出的交换器名称
     * @return
     */


    /**
     * 指定接收的交换器名称
     * @return
     */
    @Input(INPUT)
    SubscribableChannel input();
    @Output(OUTPUT)
    MessageChannel send();
}
