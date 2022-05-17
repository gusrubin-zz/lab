package com.gusrubin.labgenericsinterfaces.web.gatos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GatoResponseExtraInfoDto {
	
	String dateTime;
	String miado;

}
