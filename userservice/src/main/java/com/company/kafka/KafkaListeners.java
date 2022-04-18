package com.company.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "url", groupId = "groupId")
    public void listener(String data) {
        System.out.println("Listener received : " + data + " ");
    }
}
