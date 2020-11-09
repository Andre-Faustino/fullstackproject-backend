package com.andrefaustino.fullstackproject.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.andrefaustino.fullstackproject.domain.Address;
import com.andrefaustino.fullstackproject.domain.Category;
import com.andrefaustino.fullstackproject.domain.City;
import com.andrefaustino.fullstackproject.domain.Client;
import com.andrefaustino.fullstackproject.domain.CreditCardPayment;
import com.andrefaustino.fullstackproject.domain.InvoicePayment;
import com.andrefaustino.fullstackproject.domain.Order;
import com.andrefaustino.fullstackproject.domain.OrderItem;
import com.andrefaustino.fullstackproject.domain.Payment;
import com.andrefaustino.fullstackproject.domain.Product;
import com.andrefaustino.fullstackproject.domain.State;
import com.andrefaustino.fullstackproject.domain.enums.ClientType;
import com.andrefaustino.fullstackproject.domain.enums.PaymentState;
import com.andrefaustino.fullstackproject.repositories.AddressRepository;
import com.andrefaustino.fullstackproject.repositories.CategoryRepository;
import com.andrefaustino.fullstackproject.repositories.CityRepository;
import com.andrefaustino.fullstackproject.repositories.ClientRepository;
import com.andrefaustino.fullstackproject.repositories.OrderItemRepository;
import com.andrefaustino.fullstackproject.repositories.OrderRepository;
import com.andrefaustino.fullstackproject.repositories.PaymentRepository;
import com.andrefaustino.fullstackproject.repositories.ProductRepository;
import com.andrefaustino.fullstackproject.repositories.StateRepository;
import com.andrefaustino.fullstackproject.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	
	@Autowired
	public DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateTestDatabase();
		return true;
	}


}
