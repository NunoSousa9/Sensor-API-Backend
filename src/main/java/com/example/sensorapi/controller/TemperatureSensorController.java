package com.example.sensorapi.controller;

import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/temperature-sensors")
public class TemperatureSensorController {

    @Autowired
    private TemperatureSensorService service;

    @GetMapping
    public List<TemperatureSensor> getAll() {
        return service.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<TemperatureSensor> getSensorById(@PathVariable String id) {
        return service.findById(id)
                .map(sensorData -> ResponseEntity.ok().body(sensorData))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TemperatureSensor createSensor(@Valid @RequestBody TemperatureSensor sensorData) {
        sensorData.setType("temperature");
        return service.save(sensorData);
    }

    @PutMapping("/id")
    public ResponseEntity<TemperatureSensor> updateSensor(@PathVariable String id, @Valid @RequestBody TemperatureSensor sensorDetails) {
        return service.findById(id)
                .map(sensorData -> {
                    sensorData.setUid(sensorDetails.getUid());
                    sensorData.setValue(sensorDetails.getValue());
                    sensorData.setTimestamp(sensorDetails.getTimestamp());
                    TemperatureSensor updatedSensor = service.save(sensorData);
                    return ResponseEntity.ok().body(updatedSensor);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        return service.findById(id)
                .map(sensorData -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
