package br.com.food.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.food.entity.Cousine;
import br.com.food.entity.Store;
import br.com.food.repository.CousineRepository;
import br.com.food.repository.StoreRepository;

@RestController
public class CousineController {

	@Autowired
	private CousineRepository cousineRepository;

	@Autowired
	private StoreRepository storeRepository;

	@PostMapping("/Cousine/add")
	@ResponseBody
	public ResponseEntity<String> cousineAdd(@RequestBody Cousine cousine) {
		if (cousine.getName() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NULL");
		} else {
			if (cousine.getName().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
			} else {
				Optional<Cousine> optCousine = cousineRepository.findByName(cousine.getName());
				if (optCousine.isPresent()) {
					if (optCousine.get().getName().equals(cousine.getName())) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("There is already a cousine with this name!");
					} else {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("There is already a cousine with this name!");
					}
				} else {
					cousineRepository.save(cousine);
					return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucess");
				}
			}
		}
	}

	@GetMapping("/Cousine")
	@ResponseBody
	public List<Cousine> getAllCousine() {
		return (List<Cousine>) cousineRepository.findAll();
	}

	@GetMapping("/Cousine/search/{searchText}")
	@ResponseBody
	public ResponseEntity<Collection<Cousine>> getCousineContainName(@PathVariable("searchText") String searchText) {

		Collection<Cousine> cousines = cousineRepository.findByNameContaining(searchText);
		if (cousines.isEmpty()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ArrayList<Cousine>());
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(cousines);

		}
	}

	@GetMapping("/Cousine/{cousineId}/stores")
	@ResponseBody
	public ResponseEntity<Collection<Store>> getStoreByCousineID(@PathVariable("cousineId") Integer cousineId) {
		if (cousineId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Cousine> cousine = cousineRepository.findById(cousineId);
		if (!cousine.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
		Collection<Store> restaurant = storeRepository.findBycousine(cousine.get());
		if (restaurant.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(restaurant);
	}

}
