package com.example.sensorapi.repository;

import com.example.sensorapi.model.TemperatureSensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureSensorRepository extends MongoRepository<TemperatureSensor, String> {
}
