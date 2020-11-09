package com.andrefaustino.fullstackproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andrefaustino.fullstackproject.domain.Product;
import com.andrefaustino.fullstackproject.domain.Product;
import com.andrefaustino.fullstackproject.dto.ProductDTO;
import com.andrefaustino.fullstackproject.resources.utils.URL;
import com.andrefaustino.fullstackproject.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	//GET by ID
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Integer id){
		Product obj = service.FindById(id);
		return ResponseEntity.ok().body(obj);
	}

	//GET ALL (PAGE)
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="categories", defaultValue="") String categories,
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		
		List<Integer> ids = URL.decodeListInteger(categories);
		String decodedName = URL.encodeParam(name);
		
		Page<Product> list = service.search(decodedName, ids, page, linesPerPage, orderBy,direction);
		Page<ProductDTO> listDTO = list.map(obj -> new ProductDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}
