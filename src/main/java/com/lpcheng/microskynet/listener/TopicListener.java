package com.lpcheng.microskynet.listener;

import com.lpcheng.microskynet.service.AnalyzeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class TopicListener {

    @Autowired
    private AnalyzeService analyzeService;

    @KafkaListener(topics = {"microskynet_original"})
    public void orignalListener(String msg) throws IOException {
        log.info(msg);
        analyzeService.clean(msg);
    }

    @KafkaListener(topics = {"microskynet_cleaned"})
    public void cleanedListener(String key, String value) throws IOException {
        analyzeService.recognition(key, value);
    }
}
