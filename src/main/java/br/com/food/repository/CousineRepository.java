package br.com.food.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.food.entity.Cousine;

public interface CousineRepository extends JpaRepository<Cousine, Integer> {
	
	Optional<Cousine> findByName(@Param("name") String name);

	Collection<Cousine> findByNameContaining(@Param("name") String name);

}