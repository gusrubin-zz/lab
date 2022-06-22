package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.util.List;

import org.springframework.lang.Nullable;

public interface GetHistoryUseCase {

	List<ActionLog> findAll(@Nullable String username, @Nullable String context, @Nullable ActionType actionType);

}
