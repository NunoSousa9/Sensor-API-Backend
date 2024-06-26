package com.example.sensorapi.service;

import com.example.sensorapi.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {

    @Autowired
    private SensorServiceClient sensorServiceClient;

    public SensorData fetchSensorData(String id) {
        return sensorServiceClient.getSensorData(id);
    }
}
