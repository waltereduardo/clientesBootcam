package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;

import org.apache.kafka.common.serialization.Serializer;

public class EventSerializer implements Serializer<Event<?>> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, Event<?> data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error serializing Event", e);
		}
	}

}
