package com.example.sensorapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document(collection = "sensors")
public abstract class SensorData {
    @Id
    private String id;

    @NotNull(message = "UID cannot be null")
    private String uid;

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

    public abstract Object getValue();
    public abstract void setValue(Object value);

}
