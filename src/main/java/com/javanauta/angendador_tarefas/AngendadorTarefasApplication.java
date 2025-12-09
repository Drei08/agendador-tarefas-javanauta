package com.javanauta.angendador_tarefas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AngendadorTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngendadorTarefasApplication.class, args);
	}

}
