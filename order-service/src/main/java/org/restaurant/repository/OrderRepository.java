package org.restaurant.repository;

import org.restaurant.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
