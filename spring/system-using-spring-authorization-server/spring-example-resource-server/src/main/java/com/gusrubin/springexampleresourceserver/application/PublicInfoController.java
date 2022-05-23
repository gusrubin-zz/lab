package com.gusrubin.springexampleresourceserver.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/public")
public class PublicInfoController {

	@GetMapping
	public String getPublicInfo() {
		log.info("calling getPublicInfo()");
		return "{\"response\": \"This is a public information from resource server\"}";
	}

}
