package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dtos.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query(nativeQuery = true, value = "SELECT name "
			+ "FROM customers "
			+ "WHERE UPPER(state)= UPPER(:state)")
	List<CustomerMinProjection> searchOne(String state);
	
	@Query("SELECT new com.devsuperior.uri2602.dtos.CustomerMinDTO(objCustomer.name) "
			+ "FROM Customer objCustomer "
			+ "WHERE UPPER(objCustomer.state)= UPPER(:state)")
	List<CustomerMinDTO> searchTwo(String state);
	
	
	
}
