package com.coding.house.store.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Price {

	private BigDecimal amount;
	private PriceType type;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PriceType getType() {
		return type;
	}

	public void setType(PriceType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {

		return this.type.name() + " - " + this.amount;
	}
}
