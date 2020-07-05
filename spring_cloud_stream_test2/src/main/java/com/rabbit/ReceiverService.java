package com.rabbit;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service

public class ReceiverService {

    @StreamListener(IReceiverService.INPUT)
    public void onReceiver(Map json){
        System.out.println(json + "当前分区=1");
    }
}