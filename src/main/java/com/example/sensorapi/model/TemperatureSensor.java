package com.example.sensorapi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "temperature_sensors")
public class TemperatureSensor extends SensorData {

    @NotNull(message = "Value cannot be null")
    private Double value;
    public TemperatureSensor() {
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        if(value instanceof Double) {
            this.value = (Double) value;
        } else if (value instanceof String) {
            this.value = Double.parseDouble((String) value);
        } else {
            throw new IllegalArgumentException("Value must be a Double");
        }

    }
}
