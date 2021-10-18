package com.maxidelisio.microserver.customer.service;

import java.util.List;

import com.maxidelisio.microserver.customer.dao.entity.Customer;
import com.maxidelisio.microserver.customer.dao.entity.Region;


public interface CustomerService {
	
	public List<Customer> findAllCustomer();	
	public Customer findById(Long id);	
	public Customer createCustomer(Customer product);	
	public Customer updateCustomer(Customer product);	
	public Customer deleteCustomer(Long id);	
	public List<Customer> findCustomerByRegion(Region region);
	


}
