package com.example.sensorapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Document(collection = "luminosity_sensors")
public class LuminositySensor extends SensorData {

    @NotNull(message = "Value cannot be null")
    @Min(value = 0, message = "Value must be greater than or equal to 0")
    private Integer value;

    public LuminositySensor() {
        setType("luminosity");
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Integer) {
            this.value = (Integer) value;
        } else {
            throw new IllegalArgumentException("Value must be an Integer");
        }
    }
}

