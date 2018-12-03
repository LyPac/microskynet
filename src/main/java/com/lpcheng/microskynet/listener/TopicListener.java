package com.lpcheng.microskynet.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TopicListener {
    @KafkaListener(topics = {"test"})
    public void testListener(String msg) {
        log.info(msg);
    }
}
