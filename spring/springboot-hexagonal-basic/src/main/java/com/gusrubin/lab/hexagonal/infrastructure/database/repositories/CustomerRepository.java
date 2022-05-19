package com.gusrubin.lab.hexagonal.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.hexagonal.infrastructure.database.entities.CustomerEntity;

/**
 * @author Gustavo Rubin
 */

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
