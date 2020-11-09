package com.andrefaustino.fullstackproject.services;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

	// Init Testing Code

	@Autowired
	private CategoryRepository catRepo;

	@Autowired
	private ProductRepository prodRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private ClientRepository clieRepo;

	@Autowired
	private AddressRepository addRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private PaymentRepository payRepo;

	@Autowired
	private OrderItemRepository oiRepo;

	public void instantiateTestDatabase() {
		Category cat1 = new Category(null, "Escritório");
		Category cat2 = new Category(null, "Informática");
		Category cat3 = new Category(null, "Higiene");
		Category cat4 = new Category(null, "Utensílios Domésticos");
		Category cat5 = new Category(null, "Gráfica");
		Category cat6 = new Category(null, "Consumíveis");
		Category cat7 = new Category(null, "DIY");

		Product p1 = new Product(null, "Computador", 2000.);
		Product p2 = new Product(null, "Impressora", 800.);
		Product p3 = new Product(null, "Mouse", 80.);
		Product p4 = new Product(null, "Mesa de escritório", 300.);
		Product p5 = new Product(null, "Toalha", 50.);
		Product p6 = new Product(null, "Colcha", 200.);
		Product p7 = new Product(null, "TV true color", 1200.);
		Product p8 = new Product(null, "Roçadeira", 900.);
		Product p9 = new Product(null, "Abajour", 100.);
		Product p10 = new Product(null, "Pendente", 100.);
		Product p11 = new Product(null, "Shampoo", 90.);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		catRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prodRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlândia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepo.saveAll(Arrays.asList(state1, state2));
		cityRepo.saveAll(Arrays.asList(city1, city2, city3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "38902504739", ClientType.PESSOAFISICA);
		cli1.getPhoneNumbers().addAll(Arrays.asList("970134359", "456789123"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 40", "Jd. Rosas", "123562456", cli1, city1);
		Address e2 = new Address(null, "Rua Olimpio de campos", "621", "25", "Jd. Vila Formosa", "035698456", cli1,
				city2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clieRepo.saveAll(Arrays.asList(cli1));
		addRepo.saveAll(Arrays.asList(e1, e2));

		Order ped1 = new Order(null, Instant.parse("2017-09-30T10:32:00Z"), cli1, e1);
		Order ped2 = new Order(null, Instant.parse("2017-10-10T19:35:00Z"), cli1, e2);

		cli1.getOrder().addAll(Arrays.asList(ped1, ped2));
		Payment pagto1 = new CreditCardPayment(null, PaymentState.PAID, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new InvoicePayment(null, PaymentState.PENDING, ped2, Instant.parse("2017-10-20T00:00:00Z"),
				null);
		ped2.setPayment(pagto2);

		orderRepo.saveAll(Arrays.asList(ped1, ped2));
		payRepo.saveAll(Arrays.asList(pagto1, pagto2));

		OrderItem oi1 = new OrderItem(ped1, p1, 0., 1, 2000.);
		OrderItem oi2 = new OrderItem(ped1, p3, 0., 2, 80.);
		OrderItem oi3 = new OrderItem(ped2, p2, 100., 1, 800.);

		ped1.getItems().addAll(Arrays.asList(oi2, oi2));
		ped2.getItems().addAll(Arrays.asList(oi3));

		p1.getItems().addAll(Arrays.asList(oi1));
		p2.getItems().addAll(Arrays.asList(oi3));
		p3.getItems().addAll(Arrays.asList(oi2));

		oiRepo.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}
