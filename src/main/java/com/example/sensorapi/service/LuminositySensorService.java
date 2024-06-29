package com.example.sensorapi.service;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.repository.LuminositySensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LuminositySensorService {
    @Autowired
    private LuminositySensorRepository repository;

    public List<LuminositySensor> findAll() {
        return repository.findAll();
    }

    public Optional<LuminositySensor> findById(String id) {
        return repository.findById(id);
    }

    public LuminositySensor save(LuminositySensor sensor) {
        sensor.setType("luminosity");
        return repository.save(sensor);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
