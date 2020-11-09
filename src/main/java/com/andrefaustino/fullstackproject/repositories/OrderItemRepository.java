package com.andrefaustino.fullstackproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.andrefaustino.fullstackproject.domain.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
