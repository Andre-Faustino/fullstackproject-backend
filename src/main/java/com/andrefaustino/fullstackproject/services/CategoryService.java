package com.andrefaustino.fullstackproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.dto.CategoryDTO;
import com.andrefaustino.fullstackproject.repositories.CategoryRepository;
import com.andrefaustino.fullstackproject.services.exceptions.DataIntegrityException;
import com.andrefaustino.fullstackproject.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	

	public List<Category> findAll() {
		List<Category> list = repo.findAll();
		return list;
	}
	
	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null); //guarantee that repo's method "save" creates a new registry instead of updating a existing one with same id 
		return repo.save(obj);
	}

	public Category update(Category obj) {
		Category newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void deleteById(Integer id) {
		findById(id);
		try{
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("It is NOT allowed to exclude a category that has products");
		}
	}

	public Category fromDTO(CategoryDTO objDto) {
		return new Category(objDto.getId(),objDto.getName());
	}
	
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}
}
