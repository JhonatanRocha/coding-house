package com.coding.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;

@Controller
public class ProductsController {
	
	@Autowired
    private ProductDAO productDAO;
	
    @RequestMapping("/products/form")
    public ModelAndView form(){
    	ModelAndView modelAndView = new ModelAndView("products/form");
        modelAndView.addObject("types", PriceType.values());

        return modelAndView;
    }
    
    @RequestMapping("/products")
    public String save(Product product) {
        System.out.println(product);
        productDAO.save(product);

        return "/products/ok";
    }
}
