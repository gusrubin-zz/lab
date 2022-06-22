package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import org.springframework.util.StringUtils;

public enum ActionType {
	// @formatter:off
	CREATE,
	UPDATE,
	DELETE;
	// 	@formatter:on

	public static ActionType getByNameIgnoreCase(String name) {
		ActionType actionType = null;

		if (StringUtils.hasText(name)) {
			try {
				actionType = ActionType.valueOf(name.toUpperCase());

			} catch (Exception e) {
				throw new IllegalArgumentException("Invalid action. It can be " + ActionType.CREATE + ", "
						+ ActionType.UPDATE + " or " + ActionType.DELETE);
			}
		}

		return actionType;
	}

}
