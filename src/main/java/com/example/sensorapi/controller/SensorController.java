package com.example.sensorapi.controller;

import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.service.LuminositySensorService;
import com.example.sensorapi.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private TemperatureSensorService temperatureSensorService;

    @Autowired
    private LuminositySensorService luminositySensorService;

    @GetMapping
    public List<SensorData> getAllSensors() {
        List<SensorData> allSensors = new ArrayList<>();
        allSensors.addAll(temperatureSensorService.findAll());
        allSensors.addAll(luminositySensorService.findAll());
        return allSensors;
    }
}
