package com.maxidelisio.microserver.customer.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxidelisio.microserver.customer.dao.entity.Customer;
import com.maxidelisio.microserver.customer.dao.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	public Customer findByNumberId(String numberID);
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByRegion(Region region);

}
