package com.example.sensorapi.controller;

import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.security.TokenUtil;
import com.example.sensorapi.service.TemperatureSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors/temperature")
public class TemperatureSensorController {

    @Autowired
    private TemperatureSensorService temperatureSensorService;

    @GetMapping
    public List<TemperatureSensor> getAll() {
        return temperatureSensorService.findAll();

    }

    @GetMapping("/id")
    public Optional<TemperatureSensor> getSensorById(@PathVariable String id) {
        return temperatureSensorService.findById(id);
    }

    @PostMapping
    public TemperatureSensor createSensor(@RequestBody @Valid TemperatureSensor sensorData) {
        sensorData.setType("temperature");
        return temperatureSensorService.save(sensorData);

    }

    @PutMapping("/id")
    public TemperatureSensor updateSensor(@PathVariable String id, @RequestBody TemperatureSensor sensorDetails) {
        sensorDetails.setId(id);
        return temperatureSensorService.save(sensorDetails);

    }

    @DeleteMapping("/id")
    public void deleteSensor(@PathVariable String id) {
        temperatureSensorService.deleteById(id);
    }
}
