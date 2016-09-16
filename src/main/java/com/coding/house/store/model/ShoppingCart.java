package com.coding.house.store.model;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {

	private Map<ShoppingCartItem, Integer> itens = new LinkedHashMap<ShoppingCartItem, Integer>();
	
	public void add(ShoppingCartItem shoppingCartItem) {
		itens.put(shoppingCartItem, getQuantity(shoppingCartItem) + 1);
	}

	private int getQuantity(ShoppingCartItem shoppingCartItem) {
		
		if(!itens.containsKey(shoppingCartItem))
			itens.put(shoppingCartItem, 0);
			
		return itens.get(shoppingCartItem);
	}
	
	public int getQuantity() {
		//Java 8 Lambda
		return itens.values().stream().reduce(0, (next, accumulator) -> (next + accumulator));
	}
}
