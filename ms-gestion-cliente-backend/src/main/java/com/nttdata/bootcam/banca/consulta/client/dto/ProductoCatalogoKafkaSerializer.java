package com.nttdata.bootcam.banca.consulta.client.dto;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductoCatalogoKafkaSerializer implements Serializer<ProductoCatalogoKafka> {
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, ProductoCatalogoKafka data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (Exception e) {
			throw new RuntimeException("Error al serializar el objeto ProductoCatalogoKafka", e);
		}
	}
}
