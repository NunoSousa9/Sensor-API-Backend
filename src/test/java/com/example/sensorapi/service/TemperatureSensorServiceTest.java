package com.example.sensorapi.service;

import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.repository.TemperatureSensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TemperatureSensorServiceTest {

    @InjectMocks
    private TemperatureSensorService service;

    @Mock
    private TemperatureSensorRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSensors() {
        when(repository.findAll()).thenReturn(Arrays.asList(new TemperatureSensor(), new TemperatureSensor()));

        List<TemperatureSensor> sensors = service.findAll();
        assertEquals(2, sensors.size());
    }


}
