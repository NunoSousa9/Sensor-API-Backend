package com.example.sensorapi.service;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.model.TemperatureSensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumerService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "sensor-data-queue")
    public void processMessage(String message) {
        try {
            SensorData sensorData = objectMapper.readValue(message, SensorData.class);
            if (sensorData instanceof TemperatureSensor) {
                TemperatureSensor temperatureSensor = (TemperatureSensor) sensorData;
                System.out.println("Received TemperatureSensor: " + temperatureSensor.getValue());
            } else if (sensorData instanceof LuminositySensor) {
                LuminositySensor luminositySensor = (LuminositySensor) sensorData;
                System.out.println("Received LuminositySensor: " + luminositySensor.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
