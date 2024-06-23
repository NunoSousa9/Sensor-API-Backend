package com.example.sensorapi.controller;

import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    public List<SensorData> getAllSensors() {
        return sensorRepository.findAll();
    }

    @PostMapping
    public SensorData createSensor(@RequestBody SensorData sensorData) {
        return sensorRepository.save(sensorData);
    }
}
