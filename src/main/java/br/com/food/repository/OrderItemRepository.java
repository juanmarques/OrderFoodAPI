package br.com.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import br.com.food.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByclientOrder_id (@Param("clientOrder_id") Integer clientOrder_id);
}