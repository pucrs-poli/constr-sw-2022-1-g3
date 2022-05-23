package com.trabalho.reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableFeignClients
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

}
