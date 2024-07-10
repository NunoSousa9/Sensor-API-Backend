package com.example.sensorapi;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.logging.Logger;


@SpringBootApplication(scanBasePackages = "com.example.sensorapi")
@EnableFeignClients(basePackages = "com.example.sensorapi")
@ComponentScan(basePackages = "com.example.sensorapi")
public class SensorApiApplication implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(SensorApiApplication.class.getName());

	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Autowired
	private Queue sensorDataQueue;

	public static void main(String[] args) {
		try {
			SpringApplication.run(SensorApiApplication.class, args);
		} catch (Exception e) {
			logger.severe("Application failed to start: " + e.getMessage());
		}
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitAdmin.declareQueue(sensorDataQueue);
	}
}