package dev.curtis.northroad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class NorthroadApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthroadApplication.class, args);
	}

}
