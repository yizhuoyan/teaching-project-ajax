package com.yizhuoyan.teaching.demo.core;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtil {
	private static ObjectMapper JSON_MAPPER = new ObjectMapper();
	static {

	}

	public static String toJsonString(Object o) {
		try {
			return JSON_MAPPER.writeValueAsString(o);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
