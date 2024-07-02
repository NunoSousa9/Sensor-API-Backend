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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    public void testFindById() {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(sensor));

        Optional<TemperatureSensor> foundSensor = service.findById("1");
        assertTrue(foundSensor.isPresent());
        assertEquals("1", foundSensor.get().getId());
    }

    @Test
    public void testSave() {
        TemperatureSensor sensor = new TemperatureSensor();
        when(repository.save(sensor)).thenReturn(sensor);

        TemperatureSensor savedSensor = service.save(sensor);
        assertEquals(sensor, savedSensor);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(repository).deleteById("1");

        service.deleteById("1");

        verify(repository, times(1)).deleteById("1");
    }
}
