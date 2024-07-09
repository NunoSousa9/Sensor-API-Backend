package com.example.sensorapi.repository;

import com.example.sensorapi.model.TemperatureSensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class TemperatureSensorRepositoryTest {

    @Autowired
    private TemperatureSensorRepository repository;

    @Test
    public void testSaveAndFindById() {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setUid(123L);
        sensor.setValue(25.5);
        sensor.setType("temperature");
        repository.save(sensor);

        Optional<TemperatureSensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isPresent();
        assertThat(foundSensor.get().getUid()).isEqualTo(123L);
    }

    @Test
    public void testUpdateSensor() {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setUid(123L);
        sensor.setValue(25.5);
        sensor.setType("temperature");
        repository.save(sensor);

        sensor.setValue(30.0);
        repository.save(sensor);

        Optional<TemperatureSensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isPresent();
        assertThat(foundSensor.get().getValue()).isEqualTo(30.0);
    }

    @Test
    public void testDeleteSensor() {
        TemperatureSensor sensor = new TemperatureSensor();
        sensor.setUid(123L);
        sensor.setValue(25.5);
        sensor.setType("temperature");
        repository.save(sensor);

        repository.deleteById(sensor.getId());

        Optional<TemperatureSensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isNotPresent();
    }
}
