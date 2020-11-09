package com.andrefaustino.fullstackproject.services;

import java.time.Instant;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.domain.InvoicePayment;
import com.andrefaustino.fullstackproject.domain.Order;
import com.andrefaustino.fullstackproject.domain.OrderItem;
import com.andrefaustino.fullstackproject.domain.enums.PaymentState;
import com.andrefaustino.fullstackproject.repositories.OrderItemRepository;
import com.andrefaustino.fullstackproject.repositories.OrderRepository;
import com.andrefaustino.fullstackproject.repositories.PaymentRepository;
import com.andrefaustino.fullstackproject.repositories.ProductRepository;
import com.andrefaustino.fullstackproject.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private PaymentRepository payRepo;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private OrderItemRepository oiRepo;
	
	public Order FindById(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found! Id: " + id + ", type: " + Order.class.getName()));
	}

	@Transactional
	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstant(Instant.now());
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		
		if(obj.getPayment() instanceof InvoicePayment) {
			InvoicePayment pay = (InvoicePayment) obj.getPayment();
			InvoiceService.InvoicePaymentFilling(pay, obj.getInstant());
		}
		obj = repo.save(obj);
		payRepo.save(obj.getPayment());
		
		for (OrderItem x : obj.getItems()) {
			x.setDiscount(0.0);
			x.setPrice(prodService.FindById(x.getProduct().getId()).getPrice());
			x.setOrder(obj);
		}
		oiRepo.saveAll(obj.getItems());
		
		return obj; 
	}
}
