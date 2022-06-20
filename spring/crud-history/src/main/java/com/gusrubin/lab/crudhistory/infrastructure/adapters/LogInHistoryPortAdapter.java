package com.gusrubin.lab.crudhistory.infrastructure.adapters;

import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistory.domain.history.ActionLog;
import com.gusrubin.lab.crudhistory.domain.history.LogInHistoryPort;
import com.gusrubin.lab.crudhistory.infrastructure.database.entities.HistoryEntity;
import com.gusrubin.lab.crudhistory.infrastructure.database.repositories.HistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LogInHistoryPortAdapter implements LogInHistoryPort {

	private final HistoryRepository historyRepository;
	private final ModelMapper mapper;

	public LogInHistoryPortAdapter(HistoryRepository historyRepository, ModelMapper modelMapper) {
		this.historyRepository = historyRepository;
		this.mapper = modelMapper;
	}

	@Async
	@Override
	public void save(ActionLog actionLog) {
//		logInConsole(actionLog);
		logInDatabase(actionLog);
	}

	private void logInConsole(ActionLog actionLog) {
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("[HISTORY] {}", actionLog);
	}

	private void logInDatabase(ActionLog actionLog) {
		historyRepository.save(mapper.map(actionLog, HistoryEntity.class));
	}

}
