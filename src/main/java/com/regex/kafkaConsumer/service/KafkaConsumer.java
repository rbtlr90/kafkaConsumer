package com.regex.kafkaConsumer.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.regex.kafkaConsumer.dto.AlarmDto;
import com.regex.kafkaConsumer.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private static final String TOPIC_NAME = "topic-example1";
    private static final String GROUP_ID = "alarm.group.v1";
    private final AlarmService alarmService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @KafkaListener(topics = TOPIC_NAME, groupId = GROUP_ID)
    public void recordListener(String jsonMessage) {
        try{
            AlarmDto alarmDto = objectMapper.readValue(jsonMessage, AlarmDto.class);
//            log.info(alarmDto.toString());
            this.alarmService.saveAlarm(alarmDto);
        } catch (Exception e) {
            log.error("recordListener ERROR message : {}", jsonMessage, e);
        }
    }

}
