package com.andrefaustino.fullstackproject.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrefaustino.fullstackproject.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
