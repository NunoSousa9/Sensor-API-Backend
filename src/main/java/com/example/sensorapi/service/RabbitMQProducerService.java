package com.example.sensorapi.service;

import com.example.sensorapi.config.RabbitMQConfig;
import com.example.sensorapi.model.SensorData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(SensorData sensorData) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.SENSOR_DATA_QUEUE, sensorData);
    }
}
