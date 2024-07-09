package com.example.sensorapi.repository;

import com.example.sensorapi.model.LuminositySensor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class LuminositySensorRepositoryTest {

    @Autowired
    private LuminositySensorRepository repository;

    @Test
    public void testSaveAndFindById() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setUid(123L);
        sensor.setValue(25);
        sensor.setType("luminosity");
        repository.save(sensor);

        Optional<LuminositySensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isPresent();
        assertThat(foundSensor.get().getUid()).isEqualTo(123L);
    }

    @Test
    public void testUpdateSensor() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setUid(123L);
        sensor.setValue(25);
        sensor.setType("luminosity");
        repository.save(sensor);

        sensor.setValue(30);
        repository.save(sensor);

        Optional<LuminositySensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isPresent();
        assertThat(foundSensor.get().getValue()).isEqualTo(30);
    }

    @Test
    public void testDeleteSensor() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setUid(123L);
        sensor.setValue(25);
        sensor.setType("luminosity");
        repository.save(sensor);

        repository.deleteById(sensor.getId());

        Optional<LuminositySensor> foundSensor = repository.findById(sensor.getId());
        assertThat(foundSensor).isNotPresent();
    }
}
