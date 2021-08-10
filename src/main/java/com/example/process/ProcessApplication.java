package com.example.process;

import com.example.process.config.ApplicationProperties;
import com.example.process.model.Message;
import com.example.process.model.MessageCensured;
import liquibase.integration.spring.SpringLiquibase;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;

import java.util.HashMap;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class ProcessApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProcessApplication.class, args);
	}

}
