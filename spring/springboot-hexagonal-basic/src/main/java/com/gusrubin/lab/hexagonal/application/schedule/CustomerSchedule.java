package com.gusrubin.lab.hexagonal.application.schedule;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gusrubin.lab.hexagonal.domain.customer.CustomerCrudUseCase;

import lombok.extern.slf4j.Slf4j;

/** 
 * @author Gustavo Rubin
 */

@Slf4j
@Component
public class CustomerSchedule {

	private final CustomerCrudUseCase customerCrud;

	@Autowired
	public CustomerSchedule(CustomerCrudUseCase customerCrudUseCase, ModelMapper modelMapper) {
		this.customerCrud = customerCrudUseCase;
	}

	@Scheduled(fixedRate = 5000)
	public void listCustomers() {
		log.info("Periodic list of customers: {}", customerCrud.findAll());
	}

}
