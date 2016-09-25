package com.coding.house.store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.house.store.dao.ProductDAO;
import com.coding.house.store.infra.FileSaver;
import com.coding.house.store.model.PriceType;
import com.coding.house.store.model.Product;
import com.coding.house.store.validation.ProductValidation;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
    private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
    @RequestMapping("/form")
    public ModelAndView form(Product product) {
    	ModelAndView modelAndView = new ModelAndView("products/form");
        modelAndView.addObject("types", PriceType.values());

        return modelAndView;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    @CacheEvict(value="productsHome", allEntries=true)
    public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes) {
        
    	System.out.println(summary.getOriginalFilename());
    	
    	if(result.hasErrors())
    		return form(product);
    	
    	String path = fileSaver.write("summary-files", summary);
    	product.setSummaryPath(path);
    	
        productDAO.save(product);
        redirectAttributes.addFlashAttribute("success", "Product was successful registred.");
        return new ModelAndView("redirect:products");
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView list() {
    	List<Product> products = productDAO.list();
    	ModelAndView modelAndView = new ModelAndView("products/list");
    	modelAndView.addObject("products", products);
    	return modelAndView;
    	
    	//An alternative using string as return:
    	
    	/* model.addAttribute("produtos", produtos);
        return "produtos/lista"; */
    }
    
    @RequestMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Integer id) {
    	ModelAndView modelAndView = new ModelAndView("products/details");
    	Product product = productDAO.find(id);
    	modelAndView.addObject("product", product);
    	
    	return modelAndView;
    }
    
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
    	binder.addValidators(new ProductValidation());
    }
}
