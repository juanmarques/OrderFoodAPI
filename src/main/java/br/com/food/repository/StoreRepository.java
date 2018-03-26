package br.com.food.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.food.entity.Cousine;
import br.com.food.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {

	Collection<Store> findBycousine(@Param("cousine") Cousine cousine);

	Optional<Store> findByname(@Param("name") String name);

	Collection<Store> findByNameContaining(@Param("name") String name);

}
