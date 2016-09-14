package com.coding.house.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;

@Controller
@RequestMapping("products")
public class ProductsController {
	
	@Autowired
    private ProductDAO productDAO;
	
    @RequestMapping("/form")
    public ModelAndView form(){
    	ModelAndView modelAndView = new ModelAndView("products/form");
        modelAndView.addObject("types", PriceType.values());

        return modelAndView;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView save(Product product, RedirectAttributes redirectAttributes) {
        System.out.println(product);
        productDAO.save(product);
        redirectAttributes.addFlashAttribute("success", "Product was successful registred.");
        return new ModelAndView("redirect:products");
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView list(){
    	List<Product> products = productDAO.list();
    	ModelAndView modelAndView = new ModelAndView("products/list");
    	modelAndView.addObject("products", products);
    	return modelAndView;
    	
    	//An alternative using string as return:
    	
    	/* model.addAttribute("produtos", produtos);
        return "produtos/lista"; */
    }
}
