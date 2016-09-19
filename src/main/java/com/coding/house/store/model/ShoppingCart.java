package com.coding.house.store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<ShoppingCartItem, Integer> itens = new LinkedHashMap<ShoppingCartItem, Integer>();
	
	public void add(ShoppingCartItem shoppingCartItem) {
		itens.put(shoppingCartItem, getQuantity(shoppingCartItem) + 1);
	}
	
	public Collection<ShoppingCartItem> getItens() {
		return itens.keySet();
	}

	public int getQuantity(ShoppingCartItem shoppingCartItem) {
		
		if(!itens.containsKey(shoppingCartItem))
			itens.put(shoppingCartItem, 0);
			
		return itens.get(shoppingCartItem);
	}
	
	public int getQuantity() {
		//Java 8 Lambda
		return itens.values().stream().reduce(0, (next, accumulator) -> (next + accumulator));
	}
	
	public BigDecimal getTotal(ShoppingCartItem shoppingCartItem) {
		return shoppingCartItem.getTotal(getQuantity(shoppingCartItem));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (ShoppingCartItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}

	//Fabio Francisco 
	//keiwkxat
	
	public void remove(Integer productId, PriceType priceType) {
		Product product = new Product();
		product.setId(productId);
		itens.remove(new ShoppingCartItem(product, priceType));
	}
}
