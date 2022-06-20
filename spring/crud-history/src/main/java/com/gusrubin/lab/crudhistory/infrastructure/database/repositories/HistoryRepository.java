package com.gusrubin.lab.crudhistory.infrastructure.database.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.crudhistory.domain.history.ActionLog;
import com.gusrubin.lab.crudhistory.infrastructure.database.entities.HistoryEntity;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

//	List<HistoryEntity> findByUsername(String username);
//
//	List<HistoryEntity> findByContext(String context);
//
//	List<HistoryEntity> findByAction(String action);

}
