package br.com.food.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.food.entity.CliOrder;
import br.com.food.entity.CustomClientOrderSerializer;
import br.com.food.entity.Customer;
import br.com.food.entity.OrderItem;
import br.com.food.entity.Product;
import br.com.food.repository.ClientOrderRepository;
import br.com.food.repository.CustomerRepository;
import br.com.food.repository.OrderItemRepository;
import br.com.food.repository.ProductRepository;

public class OrderController {

	@Autowired
	private ClientOrderRepository CliOrderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@PostMapping("/Order/add")
	@ResponseBody
	public ResponseEntity<String> addProductToOrder(@RequestBody CliOrder CliOrder) {
		if (CliOrder.getDeliveryAddress() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No delivery adress set");
		}
		if (CliOrder.getCustomer().getEmail().isEmpty() || CliOrder.getCustomer().getPassword().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email or password set");
		}
		Optional<Customer> optCustomer = customerRepository.findByEmail(CliOrder.getCustomer().getEmail());

		if (optCustomer.isPresent()) {
			if (!optCustomer.get().getEmail().equals(CliOrder.getCustomer().getEmail())) {
				if (optCustomer.get().getPassword().equals(CliOrder.getCustomer().getPassword())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email or password set");
				}
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No email found");
		}
		if (CliOrder.getDeliveryAddress().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No delivery adress set.");
		}
		if (CliOrder.getOrdemItems().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no Products");
		}
		for (OrderItem p : CliOrder.getOrdemItems()) {
			Optional<Product> optProduct = productRepository.findByName(p.getProduct().getName());
			if (!optProduct.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no product registed");
			}
			if (!optProduct.get().getName().equals(p.getProduct().getName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
			}
		}
		CliOrder.setCustomer(optCustomer.get());
		CliOrder.setTotal(0);
		CliOrder.setDate(new Date(0).toString());
		CliOrder.setLastUpdate(new Date(0).toString());
		CliOrder.setStatus("Order requested");

		Collection<OrderItem> intList = new ArrayList<>(CliOrder.getOrdemItems());
		CliOrder.setOrdemItems(new ArrayList<>());

		CliOrder = CliOrderRepository.save(CliOrder);

		CliOrder CliOrderupdate = CliOrderRepository.getOne(CliOrder.getId());
		for (OrderItem p : intList) {
			Optional<Product> optProduct = productRepository.findByName(p.getProduct().getName());
			p.setProduct(optProduct.get());
			CliOrderupdate.setTotal((p.getProduct().getPrice() * p.getQuantity()) + CliOrder.getTotal());
			p.setTotal(p.getTotal() + (p.getProduct().getPrice() * p.getQuantity()));
			p.setClientOrder(CliOrder);
			orderItemRepository.save(p);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucess");

	}

	@GetMapping("/Order/{orderId}")
	@ResponseBody
	public ResponseEntity<String> getOrderById(@PathVariable("orderId") Integer orderId) {

		Optional<CliOrder> optCliOrder = CliOrderRepository.findById(orderId);
		if (optCliOrder.isPresent()) {

			ObjectMapper mapper = new ObjectMapper();
			SimpleModule module = new SimpleModule("CustomCarSerializer", new Version(1, 0, 0, null, null, null));
			module.addSerializer(CliOrder.class, new CustomClientOrderSerializer());
			mapper.registerModule(module);
			String clientOrderJson;
			try {
				clientOrderJson = mapper.writeValueAsString(optCliOrder.get());

			} catch (JsonProcessingException e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(clientOrderJson);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

	}

	@PostMapping("/Order/customer/")
	@ResponseBody
	public ResponseEntity<List<CliOrder>> getProductContainName(@RequestBody Customer customer) {
		if (customer == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Customer> optCustomer = customerRepository.findByEmail(customer.getEmail());
		if (!optCustomer.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		List<CliOrder> listTemp = new ArrayList<>();

		optCustomer.get().getListaOrden().stream().map((CliOrder CliOrder) -> {
			CliOrder.setOrdemItems(orderItemRepository.findByclientOrder_id(CliOrder.getId()));
			return CliOrder;
		}).forEachOrdered((CliOrder) -> {
			listTemp.add(CliOrder);
		});
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(listTemp);
	}

	@GetMapping("/Order/cancel/{orderId}")
	@ResponseBody
	public ResponseEntity<CliOrder> cancelOrderById(@PathVariable("orderId") Integer orderId) {

		Optional<CliOrder> optCliOrder = CliOrderRepository.findById(orderId);
		if (optCliOrder.isPresent()) {
			optCliOrder.get().setStatus("Cancel");
			CliOrderRepository.saveAndFlush(optCliOrder.get());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(optCliOrder.get());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);

	}

}
