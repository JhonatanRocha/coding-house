package com.coding.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;
import com.coding.house.store.model.ShoppingCart;
import com.coding.house.store.model.ShoppingCartItem;

@Controller
@RequestMapping("/shoppingCart")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class ShoppingCartController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ShoppingCart shoppingCart;
	
	@RequestMapping("/add")
	public ModelAndView add(Integer productId, PriceType priceType) {
		ModelAndView modelAndView = new ModelAndView("redirect:/products");
		ShoppingCartItem shoppingCartItem = createItem(productId, priceType);
		shoppingCart.add(shoppingCartItem);
		
		return modelAndView;
	}

	private ShoppingCartItem createItem(Integer productId, PriceType priceType) {
		Product product = productDAO.find(productId);
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, priceType);
		return shoppingCartItem;
	}
}
