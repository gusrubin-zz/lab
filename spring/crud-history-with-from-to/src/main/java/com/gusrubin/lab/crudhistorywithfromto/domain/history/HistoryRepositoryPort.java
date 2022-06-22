package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.util.List;

public interface HistoryRepositoryPort {

	List<ActionLog> findAll();

	List<ActionLog> findAll(String username, String context, String action);

}
