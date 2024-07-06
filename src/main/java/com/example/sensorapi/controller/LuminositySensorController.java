package com.example.sensorapi.controller;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.service.LuminositySensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensors/luminosity")
public class LuminositySensorController {

    @Autowired
    private LuminositySensorService luminositySensorService;

    @GetMapping
    public List<LuminositySensor> getAll() {
        return luminositySensorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LuminositySensor> getSensorById(@PathVariable String id) {
        return luminositySensorService.findById(id);
    }

    @PostMapping
    public LuminositySensor createSensor(@RequestBody @Valid LuminositySensor sensorData) {
        sensorData.setType("luminosity");
        return luminositySensorService.save(sensorData);
    }

    @PutMapping("/{id}")
    public LuminositySensor updateSensor(@PathVariable String id, @RequestBody LuminositySensor sensorDetails) {
        sensorDetails.setId(id);
        return luminositySensorService.save(sensorDetails);

    }

    @DeleteMapping("/{id}")
    public void deleteSensor(@PathVariable String id) {
        luminositySensorService.deleteById(id);
    }
}
