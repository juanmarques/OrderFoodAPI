package com.skip.challenge.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.skip.challenge.model.Product;

@Repository
public class ProductRespository {

	@Autowired
	JdbcTemplate jdbctemplate;
	
	public List<Product> findAll() {
		return jdbctemplate.query("Select * from product", new BeanPropertyRowMapper<Product>(Product.class));
	}

	public Product findbyId(int id) {
		return jdbctemplate.queryForObject("Select * from product where id = ?",
				new BeanPropertyRowMapper<Product>(Product.class), id);
	}

	public Product findbyName(String name) {
		return jdbctemplate.queryForObject("Select * from product where name = ?",
				new BeanPropertyRowMapper<Product>(Product.class), name);
	}

}
