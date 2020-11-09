package com.andrefaustino.fullstackproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrefaustino.fullstackproject.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
