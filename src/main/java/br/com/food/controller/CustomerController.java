package br.com.food.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.food.entity.Customer;
import br.com.food.repository.CustomerRepository;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping("/Customer")
	@ResponseBody
	public ResponseEntity<String> customerAdd(@RequestBody Customer customer) {
		if (customer.getAddress() == null || customer.getEmail() == null || customer.getName() == null
				|| customer.getPassword() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		if (customer.getAddress().isEmpty() || customer.getEmail().isEmpty() || customer.getName().isEmpty()
				|| customer.getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		Optional<Customer> optcustomer = customerRepository.findByEmail(customer.getEmail());
		if (optcustomer.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There's already an account with this e-mail");
		} else {
			customer.setCreation(String.valueOf(new Date()));
			customerRepository.save(customer);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucess");
		}
	}
}
