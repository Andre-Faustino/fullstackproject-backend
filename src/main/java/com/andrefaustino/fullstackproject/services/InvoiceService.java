package com.andrefaustino.fullstackproject.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.andrefaustino.fullstackproject.domain.InvoicePayment;

public class InvoiceService {

	public static void InvoicePaymentFilling(InvoicePayment pay, Instant instant) {
		instant = instant.plus(7,  ChronoUnit.DAYS);
		pay.setDuoDate(instant);
	}; 
}
