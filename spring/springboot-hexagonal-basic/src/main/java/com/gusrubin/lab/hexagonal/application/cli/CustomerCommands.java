package com.gusrubin.lab.hexagonal.application.cli;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import com.gusrubin.lab.hexagonal.domain.customer.Customer;
import com.gusrubin.lab.hexagonal.domain.customer.CustomerCrudUseCase;

/** 
 * @author Gustavo Rubin
 */

@ShellComponent
public class CustomerCommands {

	private final CustomerCrudUseCase customerCrud;

	@Autowired
	public CustomerCommands(CustomerCrudUseCase customerCrudUseCase, ModelMapper modelMapper) {
		this.customerCrud = customerCrudUseCase;
	}

	@ShellMethod(value = "Create a customer with parameters -n 'name' -e 'email'", group = "customer", key = "customer create")
	public String create(@ShellOption(value = { "-n", "--name" }, help = "-n 'name'}") String name,
			@ShellOption(value = { "-e", "--email" }, help = "-n 'email'") String email) {

		Customer customer = Customer.builder().name(name).email(email).build();
		Customer persistedCustomer = customerCrud.create(customer);

		return "Created " + persistedCustomer.toString() + "\n";

	}

	@ShellMethod(value = "List all customer", group = "customer", key = "customer list")
	public String list() {

		StringBuilder responseStringBuilder = new StringBuilder();
		final List<Customer> customerList = customerCrud.findAll();
		for (int i = 0; i < customerList.size(); i++) {
			responseStringBuilder.append(customerList.get(i).toString()).append("\n");
		}

		return responseStringBuilder.toString();

	}

	@ShellMethod(value = "Update a customer with parameters -i 'id', -n 'name' and/or -e 'email'", group = "customer", key = "customer update")
	public String update(@ShellOption(value = { "-i", "--id" }, help = "-n 'id'}") Long id,
			@ShellOption(value = { "-n", "--name" }, defaultValue = "", help = "-n 'name'}") String name,
			@ShellOption(value = { "-e", "--email" }, defaultValue = "", help = "-n 'email'") String email) {

		Customer updatedCustomer = customerCrud.update(id, Customer.builder().name(name).email(email).build());

		return "Updated " + updatedCustomer.toString() + "\n";

	}

	@ShellMethod(value = "Delete a customer with parameters -i 'id'", group = "customer", key = "customer delete")
	public String delete(@ShellOption(value = { "-i", "--id" }, help = "-i 'id'") String idParam) {

		Long id = Long.valueOf(idParam);

		Customer persistedCustomer = customerCrud.findById(id);
		customerCrud.delete(id);

		return "Deleted " + persistedCustomer.toString() + "\n";

	}

}
