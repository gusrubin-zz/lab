package com.gusrubin.lab.crudhistory.infrastructure.database.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history")
public class HistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "occurred_at")
	private LocalDateTime occurredAt;
	@Column(name = "username")
	private String username;
	@Column(name = "context")
	private String context;
	@Column(name = "action")
	private String action;
	@Column(name = "command_args")
	private String commandArgs;
	@Column(name = "command_result")
	private String commandResult;

}
