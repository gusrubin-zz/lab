package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ActionLogDiff<T> {

	public String diff(T objectFrom, T objectTo) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < objectFrom.getClass().getDeclaredFields().length; i++) {
			Field fieldFrom = objectFrom.getClass().getDeclaredFields()[i];
			fieldFrom.setAccessible(true);

			Field fieldTo = objectTo.getClass().getDeclaredFields()[i];
			fieldTo.setAccessible(true);

			if (!fieldFrom.get(objectFrom).equals(fieldFrom.get(objectTo))) {
				sb.append("[" + objectFrom.getClass().getDeclaredFields()[i].getName() + "] ");
				sb.append("from \"" + fieldFrom.get(objectFrom) + "\" ");
				sb.append("to \"" + fieldFrom.get(objectTo) + "\" ");
			}
		}

		return sb.toString();
	}

	public String diffJson(T objectFrom, T objectTo)
			throws IllegalArgumentException, IllegalAccessException, SecurityException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		Set<ObjectNode> json = new HashSet<>();

		for (int i = 0; i < objectFrom.getClass().getDeclaredFields().length; i++) {
			Field fieldFrom = objectFrom.getClass().getDeclaredFields()[i];
			fieldFrom.setAccessible(true);

			Field fieldTo = objectTo.getClass().getDeclaredFields()[i];
			fieldTo.setAccessible(true);

			ObjectNode jsonAttribute = mapper.createObjectNode();

			if (!fieldFrom.get(objectFrom).equals(fieldFrom.get(objectTo))) {
				ObjectNode jsonFromTo = mapper.createObjectNode();
				jsonFromTo.put("from", fieldFrom.get(objectFrom).toString());
				jsonFromTo.put("to", fieldFrom.get(objectTo).toString());

				jsonAttribute.set(objectFrom.getClass().getDeclaredFields()[i].getName(), jsonFromTo);

				json.add(jsonAttribute);
			}
		}

		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
	}

}
