package com.skip.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skip.challenge.model.Product;
import com.skip.challenge.repository.ProductRespository;

@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	public ProductRespository productRepository;

	@Autowired
	public ProductsController(ProductRespository productRepository) {
		this.productRepository = productRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Product> allProducts() {
		return this.productRepository.findAll();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Product product(@PathVariable("productId") Integer id) {
		return this.productRepository.findbyId(id);
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Product product(@PathVariable("name") String name) {
		return this.productRepository.findbyName(name);
	}

}
