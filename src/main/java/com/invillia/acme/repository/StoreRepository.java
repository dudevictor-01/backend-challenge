package com.invillia.acme.repository;

import com.invillia.acme.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Defines the repository of {@link Store}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

	/**
	 * Search stores by its name and / or address. Both params are optional
	 * @param name name of the store
	 * @param address address of the store
	 * @param pageable pageable to query the results
	 * @return returns a list of stores
	 */
	@Query(value = "select s from Store s where "
			+ "(:name is null or lower(s.name) like concat('%', lower(:name), '%')) "
			+ "and (:address is null or lower(s.address) like concat('%', lower(:address), '%'))")
	Page<Store> search(String name, String address, Pageable pageable);
}
