package com.coding.house.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.model.Product;

@Controller
public class HomeController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/")
	@Cacheable(value="produtosHome")
    public ModelAndView index() {
        System.out.println("Getting in the home of Coding House");
        List<Product> products = productDAO.list();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("products", products);
        return modelAndView;
    }
}
