package com.skip.challenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skip.challenge.model.Order;
import com.skip.challenge.model.OrderItem;
import com.skip.challenge.model.Product;
import com.skip.challenge.model.ResultModel;
import com.skip.challenge.repository.OrderRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/Order")
public class OrderController {

	public OrderRepository OrderRepository;

	@Autowired
	public OrderController(OrderRepository OrderRepository) {
		this.OrderRepository = OrderRepository;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResultModel insert(@RequestBody Order Order, OrderItem orderitem, Product product) {
		return this.OrderRepository.Post(Order, orderitem, product);
	}

}
