package com.example.sensorapi.controller;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.service.LuminositySensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/luminosity-sensors")
public class LuminositySensorController {

    @Autowired
    private LuminositySensorService service;

    @GetMapping
    public List<LuminositySensor> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LuminositySensor> getSensorById(@PathVariable String id) {
        return service.findById(id)
                .map(sensorData -> ResponseEntity.ok().body(sensorData))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LuminositySensor createSensor(@Valid @RequestBody LuminositySensor sensorData) {
        sensorData.setType("luminosity");
        return service.save(sensorData);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LuminositySensor> updateSensor(@PathVariable String id, @Valid @RequestBody LuminositySensor sensorDetails) {
        return service.findById(id)
                .map(sensorData -> {
                    sensorData.setUid(sensorDetails.getUid());
                    sensorData.setValue(sensorDetails.getValue());
                    sensorData.setTimestamp(sensorDetails.getTimestamp());
                    LuminositySensor updatedSensor = service.save(sensorData);
                    return ResponseEntity.ok().body(updatedSensor);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id) {
        return service.findById(id)
                .map(sensorData -> {
                    service.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
