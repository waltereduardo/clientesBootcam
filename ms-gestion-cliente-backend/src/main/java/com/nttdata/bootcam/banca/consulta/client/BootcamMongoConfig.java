package com.nttdata.bootcam.banca.consulta.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.nttdata.bootcam.banca.consulta.client.repository", reactiveMongoTemplateRef = "bootcamReactiveMongoTemplate")
public class BootcamMongoConfig {                

    @Value("${spring.data.mongodb.uri}")
    private String bootcamMongoUri;
    
    @Primary
    @Bean
    public MongoClient bootcamMongoClient() {
        return MongoClients.create(bootcamMongoUri);
    }

    @Bean
    public ReactiveMongoTemplate bootcamReactiveMongoTemplate() {
        return new ReactiveMongoTemplate(bootcamMongoClient(), "bootcam"); 
    }
}

