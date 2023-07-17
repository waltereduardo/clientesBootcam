package com.nttdata.bootcam.banca.consulta.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.oas.annotations.EnableOpenApi;






@SpringBootApplication
@EnableMongoRepositories("com.nttdata.bootcam.banca.consulta.client.repository") 
@ComponentScan("com.nttdata.bootcam.banca.consulta.client") 
@EnableOpenApi
@OpenAPIDefinition(info = @Info(
        title = "REST  DEFINITION, ms-gestion-client-backend",
        version = "1.0.0",
        description = "Micro  service client management"
))
public class ProyectoBootCamApplication {
   
	public static void main(String[] args) {
		SpringApplication.run(ProyectoBootCamApplication.class, args);
	}

}
