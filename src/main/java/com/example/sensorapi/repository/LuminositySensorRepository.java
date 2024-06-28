package com.example.sensorapi.repository;

import com.example.sensorapi.model.LuminositySensor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LuminositySensorRepository extends MongoRepository<LuminositySensor, String> {
}
