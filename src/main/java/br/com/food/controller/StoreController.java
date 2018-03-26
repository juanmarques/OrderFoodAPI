package br.com.food.controller;

import java.util.ArrayList;
import java.util.Collection;
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

import br.com.food.entity.Store;
import br.com.food.repository.StoreRepository;

@RestController
public class StoreController {

	@Autowired
	private StoreRepository storeRepository;

	@PostMapping("/Store/add")
	@ResponseBody
	public ResponseEntity<String> StoreAdd(@RequestBody Store Store) {
		if (Store.getName() == null || Store.getAddress() == null || Store.getCousine() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		if (Store.getName().isEmpty() || Store.getAddress().isEmpty() || Store.getCousine().getName().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
		}
		Optional<Store> optStore = storeRepository.findByname(Store.getName());
		if (optStore.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There's already a store with this name");
		}
		storeRepository.save(Store);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sucess");
	}

	@GetMapping("/Store")
	@ResponseBody
	public ResponseEntity<Collection<Store>> getAllStores() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body((Collection<Store>) storeRepository.findAll());
	}

	@GetMapping("/Store/search/{searchText}")
	@ResponseBody
	public ResponseEntity<Collection<Store>> getStoreContainName(@PathVariable("searchText") String searchText) {
		if (!searchText.isEmpty()) {
			Collection<Store> Stores = storeRepository.findByNameContaining(searchText);
			if (Stores.isEmpty()) {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ArrayList<Store>());
			} else {
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(Stores);
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
		}
	}

	@GetMapping("/Store/{storeId}")
	@ResponseBody
	public ResponseEntity<Store> getStoreByID(@PathVariable("storeId") Integer storeId) {
		if (storeId == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Optional<Store> optStoree = storeRepository.findById(storeId);
		if (!optStoree.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Store());
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(optStoree.get());
	}

}
