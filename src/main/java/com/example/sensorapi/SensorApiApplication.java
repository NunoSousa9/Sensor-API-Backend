package com.example.sensorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SensorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorApiApplication.class, args);
	}
}
