package com.skip.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skip.challenge.model.Customer;
import com.skip.challenge.model.ResultModel;
import com.skip.challenge.repository.CustomerRespository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	public CustomerRespository CustomerRepository;

	@Autowired
	public CustomerController(CustomerRespository CustomerRepository) {
		this.CustomerRepository = CustomerRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResultModel insert(@RequestBody Customer customer) {
		return this.CustomerRepository.insertCustomer(customer);
	}	
	
}
