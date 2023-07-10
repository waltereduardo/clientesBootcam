package com.nttdata.bootcam.banca.consulta.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.nttdata.bootcam.banca.repository") // Asegúrate de especificar el paquete correcto
@ComponentScan("com.nttdata.bootcam.banca") // Asegúrate de especificar el paquete correcto
public class ProyectoBootCamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoBootCamApplication.class, args);
	}

}
