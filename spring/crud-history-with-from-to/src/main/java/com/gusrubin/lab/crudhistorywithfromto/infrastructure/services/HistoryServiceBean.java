package com.gusrubin.lab.crudhistorywithfromto.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistorywithfromto.domain.history.HistoryRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.domain.history.HistoryService;

@Service
public class HistoryServiceBean extends HistoryService {

	public HistoryServiceBean(HistoryRepositoryPort historyRepositoryPort) {
		super(historyRepositoryPort);
	}

}
