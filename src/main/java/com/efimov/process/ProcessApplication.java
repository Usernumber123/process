package com.efimov.process;

import com.efimov.process.config.ApplicationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class ProcessApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProcessApplication.class, args);
	}

}
