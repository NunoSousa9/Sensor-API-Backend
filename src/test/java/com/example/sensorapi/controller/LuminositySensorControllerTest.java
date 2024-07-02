package com.example.sensorapi.controller;

import com.example.sensorapi.model.LuminositySensor;
import com.example.sensorapi.service.LuminositySensorService;
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

public class LuminositySensorControllerTest {


    private MockMvc mockMvc;

    @Mock
    private LuminositySensorService service;

    @InjectMocks
    private LuminositySensorController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllLuminositySensors() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(new LuminositySensor(), new LuminositySensor()));

        mockMvc.perform(get("/sensors/luminosity")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
