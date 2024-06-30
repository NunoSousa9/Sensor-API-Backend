package com.example.sensorapi.controller;

import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.service.LuminositySensorService;
import com.example.sensorapi.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    public ResponseEntity<List<SensorData>> getAllSensors(HttpServletRequest request) {
        List<SensorData> allSensors = new ArrayList<>();
        allSensors.addAll(temperatureSensorService.findAll());
        allSensors.addAll(luminositySensorService.findAll());
        return ResponseEntity.ok(allSensors);
    }
}