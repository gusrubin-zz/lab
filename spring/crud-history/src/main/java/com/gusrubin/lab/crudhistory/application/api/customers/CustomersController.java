package com.gusrubin.lab.crudhistory.application.api.customers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.crudhistory.domain.customer.Customer;
import com.gusrubin.lab.crudhistory.domain.customer.CustomerCrudUseCase;

@RestController
@RequestMapping("api/customers")
public class CustomersController {

	private final CustomerCrudUseCase customerCrud;

	public CustomersController(CustomerCrudUseCase customerCrudUseCase) {
		this.customerCrud = customerCrudUseCase;
	}

	@PostMapping
	public Customer post(@RequestBody Customer customer) {
		return customerCrud.create(customer);
	}

	@GetMapping
	public List<Customer> getAll() {
		return customerCrud.readAll();
	}

	@GetMapping("{id}")
	public Customer getById(@PathVariable("id") Long id) {
		return customerCrud.readById(id);
	}

	@PutMapping("{id}")
	public Customer put(@PathVariable("id") Long id, @RequestBody Customer customer) {
		return customerCrud.updateById(id, customer);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		customerCrud.deleteById(id);
	}

}
