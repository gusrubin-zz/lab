package com.gusrubin.lab.rxjavaexample.domain.capabilities;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
@Builder
public class Capabilities {
	
	private final String javaVersion;
	
	private final Long networkSpeed;

}
