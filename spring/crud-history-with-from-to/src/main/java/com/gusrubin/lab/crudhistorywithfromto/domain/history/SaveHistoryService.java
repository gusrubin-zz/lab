package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SaveHistoryService<T> implements SaveHistoryAsync<T> {

	private final LogInHistoryPort logInHistory;

	public SaveHistoryService(LogInHistoryPort logInHistoryPort) {
		this.logInHistory = logInHistoryPort;
	}

	@Override
	public void saveCreationInHistory(boolean isSuccessful, T object) {
		save(object.getClass().getSimpleName(), ActionType.CREATE, isSuccessful, object.toString());
	}

	@Override
	public void saveCreationInHistory(String context, boolean isSuccessful, T object) {
		save(context, ActionType.CREATE, isSuccessful, object.toString());
	}

	@Override
	public void saveChangeInHistory(boolean isSuccessful, T objectA, T objectB) {
		save(objectA.getClass().getSimpleName(), ActionType.UPDATE, isSuccessful, diffJson(objectA, objectB));
	}

	@Override
	public void saveChangeInHistory(String context, boolean isSuccessful, T objectA, T objectB) {
		save(context, ActionType.UPDATE, isSuccessful, diffJson(objectA, objectB));
	}

	@Override
	public void saveExclusionInHistory(boolean isSuccessful, T object) {
		save(object.getClass().getSimpleName(), ActionType.DELETE, isSuccessful, object.toString());
	}

	@Override
	public void saveExclusionInHistory(String context, boolean isSuccessful, T object) {
		save(context, ActionType.DELETE, isSuccessful, object.toString());
	}

	public String diffJson(T objectFrom, T objectTo) {
		String result = null;
		StringBuilder sbError = new StringBuilder();

		ObjectMapper mapper = new ObjectMapper();
		Set<ObjectNode> json = new HashSet<>();

		for (int i = 0; i < objectFrom.getClass().getDeclaredFields().length; i++) {
			Field fieldFrom = objectFrom.getClass().getDeclaredFields()[i];
			fieldFrom.setAccessible(true);

			Field fieldTo = objectTo.getClass().getDeclaredFields()[i];
			fieldTo.setAccessible(true);

			ObjectNode jsonAttribute = mapper.createObjectNode();

			try {
				if (!fieldFrom.get(objectFrom).equals(fieldFrom.get(objectTo))) {
					ObjectNode jsonFromTo = mapper.createObjectNode();
					jsonFromTo.put("from", fieldFrom.get(objectFrom).toString());
					jsonFromTo.put("to", fieldFrom.get(objectTo).toString());

					jsonAttribute.set(objectFrom.getClass().getDeclaredFields()[i].getName(), jsonFromTo);

					json.add(jsonAttribute);
				}

			} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				sbError.append(e.getMessage());
				sbError.append(", ");
			}
		}

		try {
			return mapper.writeValueAsString(json);

		} catch (JsonProcessingException e) {
			sbError.append(e.getMessage());
			sbError.append(", ");
		}
		log.info(result);
		log.error(sbError.toString());

		return StringUtils.hasText(sbError) ? sbError.toString() : result;
	}

	private void save(String context, ActionType actionType, boolean isSuccessful, String log) {
		ActionLog actionLog = ActionLog.builder()
		// @formatter:off
				.occurredAt(LocalDateTime.now())
				.username(null)
				.context(context.toLowerCase())
				.action(actionType)
				.successful(isSuccessful)
				.log(log)
				.build();
		// @formatter:on

		SaveHistoryService.log.info(actionLog.toString());
		logInHistory.save(actionLog);
	}

}
