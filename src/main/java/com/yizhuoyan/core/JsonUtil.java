package com.yizhuoyan.core;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtil {
	private static ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {
		JSON_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		JSON_MAPPER.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		JSON_MAPPER.setSerializationInclusion(Include.NON_NULL);
		//JSON_MAPPER.registerModule(new JavaTimeModule());
	}

	public static String toJsonString(Object o) {
		try {
			return JSON_MAPPER.writeValueAsString(o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
