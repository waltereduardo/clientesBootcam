package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nttdata.bootcam.banca.consulta.client.dto.ProductoCatalogoKafka;




@Service
public class KafkaProducerService {
	
//	private final KafkaTemplate<String, ProductoCatalogoKafka> kafkaTemplate;
//	
//    @Autowired
//    public KafkaProducerService(KafkaTemplate<String, ProductoCatalogoKafka> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
    
//    public void sendMessage(String message) {
//        kafkaTemplate.send("my-topic", message);
//    }
    /**
     * Enviar mensaje de solicitud de catalogo
     * @param message
     */
//    public void sendCatalogRequest(ProductoCatalogoKafka message) {
//    	System.out.println("----1. QUIERO UN CATALOGO -------------");
//        kafkaTemplate.send("catalog-request-topic", message);
//    }
    /**
     * Enviar mensaje de solicitud de orden
     * @param message
     */
//    public void sendOrderRequest(String message) {
//        kafkaTemplate.send("order-request-topic", message);
//    }

}
