package br.com.food.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.food.entity.Product;
import br.com.food.repository.ProductRepository;

@RestController
@RequestMapping(value = "product")
public class ProductController {

	private ProductRepository productRepository;

	public ProductController(ProductRepository repository) {
		this.productRepository = repository;
	}

	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {
	if (id == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Product> optproduct = productRepository.findById(id);
		if (!optproduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Product());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(optproduct.get());
	}

	@RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
		if (name == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Product> optproduct = productRepository.findByName(name);
		if (!optproduct.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Product());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(optproduct.get());
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> getAllProduct() {
		return (List<Product>) productRepository.findAll();
	}

}
