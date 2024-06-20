package com.example.sensorapi.repository;

import com.example.sensorapi.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends MongoRepository<SensorData, String> {
}
