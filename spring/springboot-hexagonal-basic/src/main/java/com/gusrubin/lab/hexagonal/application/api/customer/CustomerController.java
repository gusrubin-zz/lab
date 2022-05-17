package com.gusrubin.lab.hexagonal.application.api.customer;

import java.util.List;

import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gusrubin.lab.hexagonal.domain.customer.Customer;
import com.gusrubin.lab.hexagonal.domain.customer.CustomerCrudUseCase;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerCrudUseCase customerCrud;
    private final ModelMapper mapper;

    @Autowired
    public CustomerController(CustomerCrudUseCase customerCrudUseCase, ModelMapper modelMapper) {
	this.customerCrud = customerCrudUseCase;
	this.mapper = modelMapper;
    }

    @PostMapping
    public CustomerDto postCustomer(@RequestBody CustomerDto requestBody) {
	Customer customer = mapper.map(requestBody, Customer.class);
	Customer createdCustomer = customerCrud.create(customer);

	return mapper.map(createdCustomer, CustomerDto.class);
    }

    @GetMapping
    public List<CustomerDto> getAllCustommers() {
	List<Customer> customerList = customerCrud.findAll();

	return customerList.stream().map(c -> mapper.map(c, CustomerDto.class)).toList();
    }

    @PutMapping("/{id}")
    public CustomerDto putCustomerById(@PathParam("id") Long id, @RequestBody CustomerDto requestBody) {
	CustomerDto responseBody = null;
	Customer customer = mapper.map(responseBody, Customer.class);
	Customer updatedCustomer = customerCrud.update(id, customer);

	return mapper.map(updatedCustomer, CustomerDto.class);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathParam("id") Long id) {
	customerCrud.delete(id);
    }

}
