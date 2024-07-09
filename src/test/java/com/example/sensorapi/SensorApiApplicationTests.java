package com.example.sensorapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SensorApiApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}

	@Test
	void applicationStarts() {
		SensorApiApplication.main(new String[]{});
	}

	@Test
	void testBeansExistence() {
		assertThat(applicationContext.containsBean("luminositySensorController")).isTrue();
		assertThat(applicationContext.containsBean("temperatureSensorController")).isTrue();
		assertThat(applicationContext.containsBean("rabbitMQConsumerService")).isTrue();
		assertThat(applicationContext.containsBean("rabbitMQProducerService")).isTrue();
	}
}
