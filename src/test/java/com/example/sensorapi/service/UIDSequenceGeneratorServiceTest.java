package com.example.sensorapi.service;

import com.example.sensorapi.model.DatabaseSequence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


public class UIDSequenceGeneratorServiceTest {

    @Mock
    private MongoOperations mongoOperations;

    @InjectMocks
    private UIDSequenceGeneratorService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateSequence() {
        DatabaseSequence counter = new DatabaseSequence("sensor_seq", 1L);
        when(mongoOperations.findAndModify(any(), any(Update.class), eq(DatabaseSequence.class)))
                .thenReturn(counter);

        long uid = service.generateSequence("sensor_seq");

        assertThat(uid).isEqualTo(1L);
    }
}
