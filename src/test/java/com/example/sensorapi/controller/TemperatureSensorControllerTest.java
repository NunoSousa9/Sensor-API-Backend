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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
