package com.andrefaustino.fullstackproject.domain;

import javax.persistence.Entity;

import com.andrefaustino.fullstackproject.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment{

	private Integer installments;
	
	public CreditCardPayment() {
		
	}

	public CreditCardPayment(Integer id, PaymentState state, Order order, Integer installments) {
		super(id, state, order);
		this.installments = installments;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}
	
	
}
