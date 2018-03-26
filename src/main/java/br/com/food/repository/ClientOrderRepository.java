package br.com.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.food.entity.CliOrder;

public interface ClientOrderRepository extends JpaRepository<CliOrder, Integer> {

	List<CliOrder> findBycustomer_id(@Param("customer_id") Integer id);

}