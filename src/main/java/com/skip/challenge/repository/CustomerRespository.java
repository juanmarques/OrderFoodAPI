package com.skip.challenge.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.skip.challenge.model.Customer;
import com.skip.challenge.model.ResultModel;

@Repository
public class CustomerRespository {

	@Autowired
	JdbcTemplate jdbctemplate;

	public ResultModel insertCustomer(Customer customer) {

		try {
			jdbctemplate.update(
					"INSERT INTO `abroad`.`customer`(`email`,`name`,`address`,`creation`,`password`)VALUES(?,?,?,?,?)",
					customer.getEmail(), customer.getName(), customer.getAddress(), customer.getCreation(),
					customer.getPassword());
		} catch (Exception e) {
			return new ResultModel(400, "error:");
		}
		return new ResultModel(200, "Sucess");
	}
}
