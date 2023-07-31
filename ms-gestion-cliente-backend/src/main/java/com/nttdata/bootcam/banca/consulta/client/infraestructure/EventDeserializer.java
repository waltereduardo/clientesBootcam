package com.nttdata.bootcam.banca.consulta.client.infraestructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.bootcam.banca.consulta.client.infraestructure.event.Event;

import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class EventDeserializer implements Deserializer<Event<?>> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Event<?> deserialize(String topic, byte[] data) {
		try {
			return objectMapper.readValue(data, Event.class);
		} catch (IOException e) {
			throw new RuntimeException("Error deserializing Event", e);
		}
	}

}
