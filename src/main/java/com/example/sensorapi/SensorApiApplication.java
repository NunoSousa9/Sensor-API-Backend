package com.example.sensorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.logging.Logger;


@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.sensorapi")
@ComponentScan(basePackages = "com.example.sensorapi", excludeFilters = @ComponentScan.Filter(pattern = "com\\.example\\.sensorapi\\.config\\.RabbitMQConfig", type = FilterType.REGEX))
public class SensorApiApplication {

	private static final Logger logger = Logger.getLogger(SensorApiApplication.class.getName());

	public static void main(String[] args) {
		try {
			SpringApplication.run(SensorApiApplication.class, args);
		} catch (Exception e) {
			logger.severe("Application failed to start: " + e.getMessage());
		}
	}
}