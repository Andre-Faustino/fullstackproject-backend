package com.andrefaustino.fullstackproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.andrefaustino.fullstackproject.domain.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
