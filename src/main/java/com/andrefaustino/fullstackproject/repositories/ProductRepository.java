package com.andrefaustino.fullstackproject.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	//method traded by next method
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj "
			+ "FROM Product obj "
			+ "INNER JOIN obj.categories cat " //Doesn't need clause ON since JPQL is Object Oriented
			+ "WHERE obj.name LIKE %:name% "
			+ "AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable pageRequest); 
	
	//This method works exactly as the above, using JPA Query along the method's name
	@Transactional(readOnly = true)
	Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);

}
