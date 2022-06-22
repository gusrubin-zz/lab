package com.gusrubin.lab.crudhistorywithfromto.infrastructure.services;

import org.springframework.stereotype.Service;

import com.gusrubin.lab.crudhistorywithfromto.domain.history.LogInHistoryPort;
import com.gusrubin.lab.crudhistorywithfromto.domain.history.SaveHistoryService;

@Service
public class SaveHistoryServiceBean<T> extends SaveHistoryService<T>{

	public SaveHistoryServiceBean(LogInHistoryPort logInHistoryPort) {
		super(logInHistoryPort);
	}

}
