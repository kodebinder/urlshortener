package com.company.kafka;

import com.company.dto.UrlDto;
import com.company.service.UserMongoServiceImpl;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListeners {

    private final UserMongoServiceImpl userMongoServiceImpl;

    public KafkaListeners(
            @Qualifier("userMongoServiceImpl") UserMongoServiceImpl userMongoServiceImpl
    ) {
        this.userMongoServiceImpl = userMongoServiceImpl;
    }

    @KafkaListener(topics = "url", groupId = "groupId")
    public void listener(String jsonDataString) {
        userMongoServiceImpl.saveUser(new Gson().fromJson(jsonDataString, UrlDto.class));
    }
}
