package com.example.sensorapi.service;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.model.TemperatureSensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RabbitMQConsumerServiceTest {

    private RabbitMQConsumerService rabbitMQConsumerService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        rabbitMQConsumerService = new RabbitMQConsumerService();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testProcessTemperatureSensorMessage() throws Exception {
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        temperatureSensor.setUid(1);
        temperatureSensor.setValue(25.0);

        String jsonMessage = objectMapper.writeValueAsString(temperatureSensor);
        rabbitMQConsumerService.processMessage(jsonMessage);

        // Verify processing logic if you have any specific checks
    }

    @Test
    public void testProcessLuminositySensorMessage() throws Exception {
        LuminositySensor luminositySensor = new LuminositySensor();
        luminositySensor.setUid(2);
        luminositySensor.setValue(100);

        String jsonMessage = objectMapper.writeValueAsString(luminositySensor);
        rabbitMQConsumerService.processMessage(jsonMessage);

        // Verify processing logic if you have any specific checks
    }
}
