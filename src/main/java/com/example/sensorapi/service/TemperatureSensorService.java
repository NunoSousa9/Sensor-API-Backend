package com.example.sensorapi.service;

import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.repository.TemperatureSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureSensorService {

    @Autowired
    private TemperatureSensorRepository repository;

    public List<TemperatureSensor> findAll() {
        return repository.findAll();
    }

    public Optional<TemperatureSensor> findById(String id) {
        return repository.findById(id);
    }

    public TemperatureSensor save(TemperatureSensor sensor) {
        sensor.setType("temperature");
        return repository.save(sensor);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
