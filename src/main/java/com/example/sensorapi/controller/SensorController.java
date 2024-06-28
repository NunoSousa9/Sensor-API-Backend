package com.example.sensorapi.controller;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.service.LuminositySensorService;
import com.example.sensorapi.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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

    @Autowired
    private TemperatureSensorController temperatureSensorController;

    @Autowired
    private LuminositySensorController luminositySensorController;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public List<SensorData> getAllSensors() {
        List<SensorData> allSensors = new ArrayList<>();
        allSensors.addAll(temperatureSensorService.findAll());
        allSensors.addAll(luminositySensorService.findAll());
        return allSensors;
    }

    @GetMapping("/temperature")
    public List<TemperatureSensor> getTemperatureSensors() {
        temperatureSensorController = applicationContext.getBean(TemperatureSensorController.class);
        return temperatureSensorController.getAll();
    }

    @PostMapping("/temperature")
    public TemperatureSensor createTemperatureSensor(@RequestBody @Valid TemperatureSensor temperatureSensor) {
        temperatureSensor.setType("temperature");
        temperatureSensorController = applicationContext.getBean(TemperatureSensorController.class);
        return temperatureSensorController.createSensor(temperatureSensor);
    }

    @PutMapping("/temperature/{id}")
    public ResponseEntity<TemperatureSensor> updateTemperatureSensor(@PathVariable String id, @RequestBody @Valid TemperatureSensor temperatureSensorDetails) {
        temperatureSensorController = applicationContext.getBean(TemperatureSensorController.class);
        return temperatureSensorController.updateSensor(id, temperatureSensorDetails);
    }

    @DeleteMapping("/temperature/{id}")
    public ResponseEntity<Void> deleteTemperatureSensor(@PathVariable String id) {
        temperatureSensorController = applicationContext.getBean(TemperatureSensorController.class);
        return temperatureSensorController.deleteSensor(id);
    }

    @GetMapping("/luminosity")
    public List<LuminositySensor> getLuminositySensors() {
        luminositySensorController = applicationContext.getBean(LuminositySensorController.class);
        return luminositySensorController.getAll();
    }

    @PostMapping("/luminosity")
    public LuminositySensor createLuminositySensor(@RequestBody @Valid LuminositySensor luminositySensor) {
        luminositySensor.setType("luminosity");
        luminositySensorController = applicationContext.getBean(LuminositySensorController.class);
        return luminositySensorController.createSensor(luminositySensor);
    }

    @PutMapping("/luminosity/{id}")
    public ResponseEntity<LuminositySensor> updateLuminositySensor(@PathVariable String id, @RequestBody @Valid LuminositySensor luminositySensorDetails) {
        luminositySensorController = applicationContext.getBean(LuminositySensorController.class);
        return luminositySensorController.updateSensor(id, luminositySensorDetails);
    }

    @DeleteMapping("/luminosity/{id}")
    public ResponseEntity<Void> deleteLuminositySensor(@PathVariable String id) {
        luminositySensorController = applicationContext.getBean(LuminositySensorController.class);
        return luminositySensorController.deleteSensor(id);
    }
}
