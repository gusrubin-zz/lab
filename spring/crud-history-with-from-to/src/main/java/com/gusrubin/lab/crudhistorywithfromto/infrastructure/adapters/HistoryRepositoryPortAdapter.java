package com.gusrubin.lab.crudhistorywithfromto.infrastructure.adapters;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.crudhistorywithfromto.domain.history.ActionLog;
import com.gusrubin.lab.crudhistorywithfromto.domain.history.HistoryRepositoryPort;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.HistoryEntity;
import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories.HistoryRepository;

@Component
public class HistoryRepositoryPortAdapter implements HistoryRepositoryPort {

	private final HistoryRepository historyRepository;
	private final ModelMapper mapper;

	public HistoryRepositoryPortAdapter(HistoryRepository historyRepository, ModelMapper modelMapper) {
		this.historyRepository = historyRepository;
		this.mapper = modelMapper;
	}

	@Override
	public List<ActionLog> findAll() {
		return historyRepository.findAll().stream().map(c -> mapper.map(c, ActionLog.class)).toList();
	}

	@Override
	public List<ActionLog> findAll(String username, String context, String action) {
		HistoryEntity historyExample = HistoryEntity.builder()
		// @formatter:off
						.username(username)
						.context(context)
						.action(action)
						.build();
		// @formatter:on

		ExampleMatcher matcher = ExampleMatcher.matchingAll()
		// @formatter:off
						.withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
						.withMatcher("context", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase())
						.withMatcher("action", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
		// @formatter:on

		Example<HistoryEntity> example = Example.of(historyExample, matcher);
		return historyRepository.findAll(example).stream().map(c -> mapper.map(c, ActionLog.class)).toList();
	}

}
