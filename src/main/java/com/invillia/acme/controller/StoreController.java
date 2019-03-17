package com.invillia.acme.controller;

import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Define a Rest Controller for the {@link Store} entity
 * @author José Victor | jvas.2000@gmail.com
 */
@RestController
@RequestMapping("/stores")
public class StoreController {

	private final StoreRepository repository;

	@Autowired
	public StoreController(StoreRepository repository) {
		this.repository = repository;
	}


	@GetMapping("/")
	public List<Store> findAll() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Store> findById(@PathVariable Long id) {
		return repository.findById(id);
	}

	/**
	 * Retrives a list of {@link Store} by its name and address.
	 * Both params are optionals and case insensitive
	 * @param name name of the store
	 * @param address address of the store
	 * @return returns a list of stores
	 */
	@GetMapping("/search")
	public List<Store> search(@RequestParam(required = false) String name, @RequestParam(required = false) String address) {
		return repository.findByNameLikeAndAddress(name, address);
	}

	@PostMapping("/")
	public Store create(@RequestBody Store store) {
		return repository.save(store);
	}

	@PutMapping("/")
	public Store update(@RequestBody Store store) {
		return repository.save(store);
	}

}
