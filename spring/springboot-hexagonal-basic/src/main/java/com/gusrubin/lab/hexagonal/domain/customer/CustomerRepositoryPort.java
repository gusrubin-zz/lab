package com.gusrubin.lab.hexagonal.domain.customer;

import java.util.List;

public interface CustomerRepositoryPort {
    
    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findById(Long id);

    void delete(Long id);

}
