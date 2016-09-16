package com.coding.house.store.model;

public class ShoppingCartItem {

	private Product product;
	private PriceType priceType;
	
	public ShoppingCartItem(Product product, PriceType priceType) {
		this.product = product;
		this.priceType = priceType;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public PriceType getPriceType() {
		return priceType;
	}
	
	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priceType == null) ? 0 : priceType.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingCartItem other = (ShoppingCartItem) obj;
		if (priceType != other.priceType)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}
