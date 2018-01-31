package com.fiberhome.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 用Spring提供的json序列化工具类
 */
public class JsonUtil {
	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}

	public static String object2Json(Object obj) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}

	public static <T> T jsonStr2Object(String jsonStr, Class<T> clazz) throws IOException {
		return mapper.readValue(jsonStr, clazz);
	}

	public static <T> T jsonNode2Object(JsonNode jsonNode, Class<T> clazz) throws JsonProcessingException {
		return mapper.treeToValue(jsonNode, clazz);
	}

	public static JsonNode jsonStr2JsonNode(String jsonStr) throws IOException {
		return mapper.readTree(jsonStr);
	}

	public static JsonNode Object2JsonNode(Object obj) {
		return mapper.valueToTree(obj);
	}

	public static JsonNode inputStreamJsonNode(InputStream inputStream) throws JsonProcessingException, IOException {
		return mapper.readTree(inputStream);
	}

	public static <T> T jsonStr2TypeReference(String jsonStr, TypeReference<T> valueTypeRef)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(jsonStr, valueTypeRef);
	}

}
