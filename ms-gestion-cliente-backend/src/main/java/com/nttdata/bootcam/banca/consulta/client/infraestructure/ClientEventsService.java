package com.nttdata.bootcam.banca.consulta.client.infraestructure;


import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.nttdata.bootcam.banca.consulta.client.dto.ProductoCatalogoKafka;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.ClientCreatedEvent;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.EventType;

import org.springframework.beans.factory.annotation.Value;

@Component
public class ClientEventsService {
	
	@Autowired
	private KafkaTemplate<String, Event<?>> producer;
	
	@Value("catalog-request-topic")
	private String topicCustomer;
	
	public void publish(ProductoCatalogoKafka client) {

		ClientCreatedEvent created = new ClientCreatedEvent();
		created.setData(client);
		created.setId(UUID.randomUUID().toString());
		created.setType(EventType.CREATED);
		created.setDate(new Date());

		this.producer.send(topicCustomer, created);
	}
}
