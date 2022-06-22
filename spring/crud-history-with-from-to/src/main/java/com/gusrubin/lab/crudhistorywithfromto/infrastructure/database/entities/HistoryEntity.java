package com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
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
//	@ElementCollection
//	@CollectionTable(name = "history_attribute_diffs_mapping", joinColumns = {
//			@JoinColumn(name = "history_id", referencedColumnName = "id") })
//	@MapKeyColumn(name = "attribute_name")
//	@Column(name = "attribute_diffs")
//	private Map<String, String> attributeDiffs;
	@Column(name = "successful")
	private Boolean successful;
	@Column(name = "log", columnDefinition = "CLOB")
	private String log;

}
