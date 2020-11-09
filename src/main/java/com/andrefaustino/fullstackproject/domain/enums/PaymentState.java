package com.andrefaustino.fullstackproject.domain.enums;

public enum PaymentState {

	PENDING(1, "Pending"),
	PAID(2, "Paid"),
	CANCELLED(3, "Cancelled");
	
	private int cod;
	private String description;

	private PaymentState(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}

	public static PaymentState toEnum(Integer id) {
		
		if (id == null) {
			return null;
		}

		for (PaymentState x : PaymentState.values()) {
			if (id.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id inválido " + id);

	}

}
