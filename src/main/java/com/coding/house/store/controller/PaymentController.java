package com.coding.house.store.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding.house.store.model.PaymentData;
import com.coding.house.store.model.ShoppingCart;
import com.coding.house.store.model.User;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender mailSender;
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public Callable<ModelAndView> checkout(@AuthenticationPrincipal User user , RedirectAttributes redirectAttributes) {
		//Anonymous class written in Java 8 to process the payment as asynchronous mode
		return () -> {
			try {
				String uri = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(uri, new PaymentData(shoppingCart.getTotal()), String.class);
				
				redirectAttributes.addFlashAttribute("success", "We received your payment with success!");
				System.out.println(response);
				
				sendEmailAcquiredProducts(user);
				
				return new ModelAndView("redirect:/products");
			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("fail", "Value is bigger than the limit.");
				return new ModelAndView("redirect:/products");
			}
		};
	}

	
	public void sendEmailAcquiredProducts(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setSubject("Your order was finished with success!");
		//mailMessage.setTo(user.getEmail());
		mailMessage.setTo("jcristianrocha@gmail.com");
		mailMessage.setText("Hi " + user.getName() + 
								" we're glad to tell you that, your order was finished with success! " + 
								" order total: " + shoppingCart.getTotal());
		mailMessage.setFrom("buy@codinghouse.com");
		
		mailSender.send(mailMessage);
	}
}
