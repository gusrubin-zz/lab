package com.gusrubin.labgenericsinterfaces.web.cachorros;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CachorroResponseExtraInfoDto {
	
	String dateTime;
	String latido;

}
