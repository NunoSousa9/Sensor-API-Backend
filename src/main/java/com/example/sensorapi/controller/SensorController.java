package com.example.sensorapi.controller;


import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.service.LuminositySensorService;
import com.example.sensorapi.service.TemperatureSensorService;
import com.example.sensorapi.service.UIDSequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private TemperatureSensorService temperatureSensorService;

    @Autowired
    private LuminositySensorService luminositySensorService;

    @Autowired
    private UIDSequenceGeneratorService uidSequenceGeneratorService;

    @GetMapping
    public List<SensorData> getAllSensors(HttpServletRequest request) {
        List<SensorData> allSensors = new ArrayList<>();
        allSensors.addAll(temperatureSensorService.findAll());
        allSensors.addAll(luminositySensorService.findAll());
        return allSensors;
    }

    @GetMapping("/current-uid")
    public long getCurrentUID() {
        return uidSequenceGeneratorService.getCurrentSequence(SensorData.SEQUENCE_NAME);
    }

    @PostMapping("/reset-sequence")
    public void resetUIDSequence() {
        uidSequenceGeneratorService.resetSequence(SensorData.SEQUENCE_NAME);
    }
}