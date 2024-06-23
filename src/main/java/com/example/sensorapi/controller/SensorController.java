package com.example.sensorapi.controller;

import com.example.sensorapi.model.SensorData;
import com.example.sensorapi.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public ResponseEntity<SensorData> getSensorById(@PathVariable String id) {
        return sensorRepository.findById(id)
                .map(sensorData -> ResponseEntity.ok().body(sensorData))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorData> updateSensor(@PathVariable String id, @Valid @RequestBody SensorData sensorDetails) {
        return sensorRepository.findById(id)
                .map(sensorData -> {
                    sensorData.setUid(sensorDetails.getUid());
                    sensorData.setValue(sensorDetails.getValue());
                    sensorData.setType(sensorDetails.getType());
                    sensorData.setTimestamp(sensorDetails.getTimestamp());
                    SensorData updateSensor = sensorRepository.save(sensorData);
                    return ResponseEntity.ok().body(updateSensor);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        return sensorRepository.findById(id)
                .map(sensorData -> {
                    sensorRepository.delete(sensorData);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
