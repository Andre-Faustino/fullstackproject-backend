package com.andrefaustino.fullstackproject;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.andrefaustino.fullstackproject.domain.*;
import com.andrefaustino.fullstackproject.domain.enums.ClientType;
import com.andrefaustino.fullstackproject.domain.enums.PaymentState;
import com.andrefaustino.fullstackproject.repositories.*;

@SpringBootApplication
public class FullstackprojectApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(FullstackprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
