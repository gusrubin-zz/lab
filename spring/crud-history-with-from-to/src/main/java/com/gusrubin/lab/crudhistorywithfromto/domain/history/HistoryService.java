package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.util.List;

import org.flywaydb.core.internal.util.StringUtils;

public class HistoryService implements GetHistoryUseCase {

	private final HistoryRepositoryPort historyRepository;

	public HistoryService(HistoryRepositoryPort historyRepository) {
		this.historyRepository = historyRepository;
	}

	@Override
	public List<ActionLog> findAll(String username, String context, ActionType actionType) {
		List<ActionLog> actionLogList;

		if (!StringUtils.hasText(username) && !StringUtils.hasText(context) && actionType == null) {
			actionLogList = historyRepository.findAll();

		} else {
			actionLogList = historyRepository.findAll(
			// @formatter:off
					StringUtils.hasText(username) ? username : null, 
					StringUtils.hasText(context) ? context : null,
					actionType != null ? actionType.name() : null);
			// @formatter:on
		}

		return actionLogList;
	}

}
