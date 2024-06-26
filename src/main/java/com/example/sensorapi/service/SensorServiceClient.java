package com.example.sensorapi.service;

import com.example.sensorapi.config.FeignConfig;
import com.example.sensorapi.model.SensorData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "sensor-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface SensorServiceClient {

    @GetMapping("/sensors/{id}")
    SensorData getSensorData(@PathVariable("id") String id);
}
