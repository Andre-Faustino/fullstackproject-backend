package com.andrefaustino.fullstackproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrefaustino.fullstackproject.domain.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
