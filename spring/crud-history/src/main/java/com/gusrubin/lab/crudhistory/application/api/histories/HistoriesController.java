package com.gusrubin.lab.crudhistory.application.api.histories;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.crudhistory.domain.history.ActionLog;
import com.gusrubin.lab.crudhistory.domain.history.ActionType;
import com.gusrubin.lab.crudhistory.domain.history.GetHistoryUseCase;

@RestController
@RequestMapping("api/histories")
public class HistoriesController {

	private final GetHistoryUseCase getHistory;

	public HistoriesController(GetHistoryUseCase getHistoryUseCase) {
		this.getHistory = getHistoryUseCase;
	}

	@GetMapping
	public List<ActionLog> getAll(@RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "context", required = false) String context,
			@RequestParam(name = "action", required = false) String action) {

		return getHistory.findAll(username, context, ActionType.getByNameIgnoreCase(action));
	}

}
