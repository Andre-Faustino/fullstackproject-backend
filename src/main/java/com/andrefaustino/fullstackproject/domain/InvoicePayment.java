package com.andrefaustino.fullstackproject.domain;

import java.time.Instant;

import javax.persistence.Entity;

import com.andrefaustino.fullstackproject.domain.enums.PaymentState;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("invoicePayment")
public class InvoicePayment extends Payment {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT")
	private Instant duoDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "GMT")
	private Instant paymentDate;
	
	public InvoicePayment() {
		
	}

	public InvoicePayment(Integer id, PaymentState state, Order order, Instant duoDate, Instant paymentDate) {
		super(id, state, order);
		this.duoDate = duoDate;
		this.paymentDate = paymentDate;
	}

	public Instant getDuoDate() {
		return duoDate;
	}

	public void setDuoDate(Instant duoDate) {
		this.duoDate = duoDate;
	}

	public Instant getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Instant paymentDate) {
		this.paymentDate = paymentDate;
	}
	
}
