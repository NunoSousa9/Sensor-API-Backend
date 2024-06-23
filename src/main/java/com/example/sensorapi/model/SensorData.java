package com.example.sensorapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Document(collection = "sensors")
public class SensorData {
    @Id
    private String id;

    @NotNull(message = "UID cannot be null")
    private String uid;

    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotNull(message = "Type cannot be null")
    @Size(min = 2, max = 20, message = "Type must be between 2 and 20 characters")
    private String type;

    @NotNull(message = "Timestamp cannot be null")
    private LocalDateTime timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
