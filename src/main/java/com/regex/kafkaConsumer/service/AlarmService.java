package com.regex.kafkaConsumer.service;

import com.regex.kafkaConsumer.domain.Alarm;
import com.regex.kafkaConsumer.dto.AlarmDto;
import com.regex.kafkaConsumer.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Long saveAlarm(AlarmDto alarmDto) {
        Alarm alarm = modelMapper.map(alarmDto, Alarm.class);
        Alarm savedAlarm = this.alarmRepository.save(alarm);
        return savedAlarm.getAlarmId();
    }
}
