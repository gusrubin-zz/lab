package com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gusrubin.lab.crudhistorywithfromto.infrastructure.database.entities.AddressEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
