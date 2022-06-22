package com.gusrubin.lab.crudhistorywithfromto.domain.history;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActionLog {

	private LocalDateTime occurredAt;
	private String username;
	private String context;
	private ActionType action;
//	private List<Map<String, String>> attributeDiffs;
	private Boolean successful;
	private String log;	

}
