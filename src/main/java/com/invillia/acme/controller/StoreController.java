package com.invillia.acme.controller;

import com.invillia.acme.configuration.FixSwaggerPageable;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

/**
 * Define a Rest Controller for the {@link Store} entity
 * @author José Victor | jvas.2000@gmail.com
 */
@RestController
@RequestMapping("/store")
public class StoreController {

	private final StoreRepository repository;

	@Autowired
	public StoreController(StoreRepository repository) {
		this.repository = repository;
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
	@FixSwaggerPageable
	public Page<Store> search(@RequestParam(required = false) String name, @RequestParam(required = false) String address,
			@ApiIgnore("Ignored because swagger ui shows the wrong params, "
					+ "instead they are explained in the fix swagger pageable annotation") Pageable pageable) {
		return repository.search(name, address, pageable);
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
