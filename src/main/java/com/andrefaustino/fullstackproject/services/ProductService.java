package com.andrefaustino.fullstackproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.Product;
import com.andrefaustino.fullstackproject.repositories.CategoryRepository;
import com.andrefaustino.fullstackproject.repositories.ProductRepository;
import com.andrefaustino.fullstackproject.services.exceptions.ObjectNotFoundException;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	@Autowired
	private CategoryRepository catRepo;
	
	public Product FindById(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", type: " + Product.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = catRepo.findAllById(ids); 
		return repo.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}

}
