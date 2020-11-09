package com.andrefaustino.fullstackproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrefaustino.fullstackproject.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
