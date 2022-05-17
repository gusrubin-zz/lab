package com.gusrubin.lab.hexagonal.domain.customer;

import java.util.List;

public interface CustomerCrudUseCase {

    Customer create(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    Customer update(Long id, Customer customer);

    void delete(Long id);

}
