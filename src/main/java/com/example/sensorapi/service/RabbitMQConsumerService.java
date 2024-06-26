package com.example.sensorapi.service;

import com.example.sensorapi.config.RabbitMQConfig;
import com.example.sensorapi.model.SensorData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerService {

    @RabbitListener(queues = RabbitMQConfig.SENSOR_DATA_QUEUE)
    public void processMessage(SensorData sensorData) {

    }
}
