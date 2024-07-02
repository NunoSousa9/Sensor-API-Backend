package com.example.sensorapi.service;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.repository.LuminositySensorRepository;
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

public class LuminositySensorServiceTest {

    @InjectMocks
    private LuminositySensorService service;

    @Mock
    private LuminositySensorRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(new LuminositySensor(), new LuminositySensor()));

        List<LuminositySensor> sensors = service.findAll();
        assertEquals(2, sensors.size());
    }

    @Test
    public void testFindById() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(sensor));

        Optional<LuminositySensor> foundSensor = service.findById("1");
        assertTrue(foundSensor.isPresent());
        assertEquals("1", foundSensor.get().getId());
    }

    @Test
    public void testSave() {
        LuminositySensor sensor = new LuminositySensor();
        when(repository.save(sensor)).thenReturn(sensor);

        LuminositySensor savedSensor = service.save(sensor);
        assertEquals(sensor, savedSensor);
    }

    @Test
    public void testDeleteById() {
        doNothing().when(repository).deleteById("1");

        service.deleteById("1");

        verify(repository, times(1)).deleteById("1");
    }
}
