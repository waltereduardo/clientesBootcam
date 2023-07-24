package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.dto.ProductoCatalogoKafka;

@Service
public class KafkaConsumerService {
//    @KafkaListener(topics = "my-topic")
//    public void receiveMessage(String message) {
//        System.out.println("Received message: " + message);
//    }
//    @KafkaListener(topics = "catalog-topic")
//    public void receiveMessage(ProductoCatalogoKafka productoCatalogoKafka) {
//        System.out.println("4. Catalogo de producto, RECIBIDO: " + productoCatalogoKafka);
//        getProductoCatalogoKafka(productoCatalogoKafka);
//    }
    
    @KafkaListener(topics = "catalog-topic")
    public void receiveCatalog(ProductoCatalogoKafka catalog) {
        System.out.println("4. Recibido el catálogo de productos: " + catalog);

    }
    
//    public ProductoCatalogoKafka getProductoCatalogoKafka(ProductoCatalogoKafka productoCatalogoKafka){
//    	return productoCatalogoKafka;
//    }
}
