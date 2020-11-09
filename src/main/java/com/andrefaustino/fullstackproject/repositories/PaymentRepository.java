package com.andrefaustino.fullstackproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrefaustino.fullstackproject.domain.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
