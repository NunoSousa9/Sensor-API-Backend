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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
}
