package com.example.sensorapi.controller;

import com.example.sensorapi.model.TemperatureSensor;
import com.example.sensorapi.service.TemperatureSensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TemperatureSensorControllerTest {


    private MockMvc mockMvc;

    @Mock
    private TemperatureSensorService service;

    @InjectMocks
    private TemperatureSensorController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllTemperatureSensors() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(new TemperatureSensor(), new TemperatureSensor()));

        mockMvc.perform(get("/sensors/temperature")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetSensorByID() throws Exception {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setId("1");
        when(service.findById(anyString())).thenReturn(Optional.of(sensor));

        mockMvc.perform(get("/sensors/temperature/id")
                .param("id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testCreateSensor() throws Exception {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setId("1");
        sensor.setType("temperature");
        when(service.save(any(TemperatureSensor.class))).thenReturn(sensor);

        mockMvc.perform(post("/sensors/temperature")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\": \"123\", \"value\": 25.5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.type").value("temperature"));
    }

    @Test
    public void testUpdateSensor() throws Exception {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setId("1");
        when(service.save(any(TemperatureSensor.class))).thenReturn(sensor);

        mockMvc.perform(put("/sensors/temperature/id")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\": \"123\", \"value\": 25.5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testDeleteSensor() throws Exception {
        doNothing().when(service).deleteById(anyString());

        mockMvc.perform(delete("/sensors/temperature/id")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
