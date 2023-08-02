package com.nttdata.bootcam.banca.consulta.client.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String convertirObjetoAJson(Object objt) {
		try {
			return objectMapper.writeValueAsString(objt);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
