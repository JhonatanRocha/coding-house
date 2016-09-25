package com.coding.house.store.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.house.store.model.PaymentData;
import com.coding.house.store.model.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<ModelAndView> checkout(RedirectAttributes redirectAttributes) {
		//Anonymous class written in Java 8 to process the payment as asynchronous mode
		return () -> {
			try {
				String uri = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(uri, new PaymentData(shoppingCart.getTotal()), String.class);
				
				redirectAttributes.addFlashAttribute("success", "We received your payment with success!");
				System.out.println(response);
						
				return new ModelAndView("redirect:/products");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("fail", "Value is bigger than the limit.");
				return new ModelAndView("redirect:/products");
			}
		};
	}
}
