package com.example.sensorapi.model;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LuminositySensorTest {

    private final Validator validator;

    public LuminositySensorTest() {
        ValidatorFactory factory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidLuminositySensor() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setUid(123L);
        sensor.setValue(25);
        sensor.setTimestamp(LocalDateTime.now());
        sensor.setType("luminosity");

        var violations = validator.validate(sensor);
        assertThat(violations).isEmpty();
    }

    @Test
    public void testInvalidLuminositySensor() {
        LuminositySensor sensor = new LuminositySensor();
        sensor.setUid(0L); // Assuming uid should be non-null
        try {
            sensor.setValue(null); // This will throw IllegalArgumentException in the setValue method
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
        sensor.setTimestamp(null);
        sensor.setType(null);

        var violations = validator.validate(sensor);
        assertThat(violations).isNotEmpty();
    }
}
