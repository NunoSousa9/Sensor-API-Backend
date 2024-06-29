package com.example.sensorapi.controller;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.service.LuminositySensorService;
import com.example.sensorapi.security.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sensors/luminosity")
public class LuminositySensorController {

    private final LuminositySensorService luminositySensorService;

    public LuminositySensorController(LuminositySensorService luminositySensorService) {
        this.luminositySensorService = luminositySensorService;
    }

    @GetMapping
    public List<LuminositySensor> getAll(HttpServletRequest request) {
        TokenUtil.validateToken(request);
        return luminositySensorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LuminositySensor> getSensorById(@PathVariable String id, HttpServletRequest request) {
        TokenUtil.validateToken(request);
        return luminositySensorService.findById(id)
                .map(sensorData -> ResponseEntity.ok().body(sensorData))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LuminositySensor> createSensor(@RequestBody LuminositySensor sensorData, HttpServletRequest request) {;
        TokenUtil.validateToken(request);
        LuminositySensor savedSensor = luminositySensorService.save(sensorData);
        return ResponseEntity.ok(savedSensor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LuminositySensor> updateSensor(@PathVariable String id, @RequestBody LuminositySensor sensorDetails, HttpServletRequest request) {
        TokenUtil.validateToken(request);
        sensorDetails.setId(id);
        LuminositySensor updatedSensorDetails = luminositySensorService.save(sensorDetails);
        return ResponseEntity.ok(updatedSensorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable String id, HttpServletRequest request) {
        TokenUtil.validateToken(request);
        luminositySensorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
