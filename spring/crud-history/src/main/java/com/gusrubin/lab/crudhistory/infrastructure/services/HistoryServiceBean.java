package com.gusrubin.lab.crudhistory.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistory.domain.history.HistoryRepositoryPort;
import com.gusrubin.lab.crudhistory.domain.history.HistoryService;

@Service
public class HistoryServiceBean extends HistoryService {

	public HistoryServiceBean(HistoryRepositoryPort historyRepositoryPort) {
		super(historyRepositoryPort);
	}

}
