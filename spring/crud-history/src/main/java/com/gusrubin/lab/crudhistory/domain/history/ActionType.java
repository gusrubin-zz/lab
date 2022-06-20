package com.gusrubin.lab.crudhistory.domain.history;

import java.util.EnumSet;

import org.flywaydb.core.internal.util.StringUtils;

public enum ActionType {

	// @formatter:off
	CREATE,
	UPDATE,
	DELETE;
	// 	@formatter:on

	public static boolean isValid(String name) {
		EnumSet<ActionType> except = EnumSet.of(ActionType.CREATE, ActionType.UPDATE, ActionType.DELETE);

		boolean valid;
		try {
			valid = except.contains(ActionType.valueOf(name));
		} catch (IllegalArgumentException e) {
			valid = false;
		}

		return valid;
	}

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
